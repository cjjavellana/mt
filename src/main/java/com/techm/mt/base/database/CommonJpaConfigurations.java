/*
 * Copyright 2014 Tech Mahindra Pte. Ltd.
 */

package com.techm.mt.base.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by cjavellana on 17/10/14.
 */
@Configuration
@ComponentScan(basePackages = "com.techm.mt.model")
@PropertySource("classpath:/META-INF/spring/default.properties")
@EnableJpaRepositories(basePackages = "com.techm.mt.base.repositories")
@EnableTransactionManagement
public class CommonJpaConfigurations {
    public static final String UNDEFINED = "**UNDEFINED**";

    //@Autowired
    //private Environment environment;

    @Autowired
    @Qualifier("databaseProperties")
    private Properties databaseProperties;

    @Value("#{ environment['entity.package'] }")
    private String entityPackage = "com.techm.mt.model";

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            return getDataSource();
        } catch (PropertyVetoException pve) {
            throw new RuntimeException("Unable to create  datasource", pve);
        }
    }

    protected DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(getDriverClassName());
        dataSource.setJdbcUrl(getUrl());
        dataSource.setUser(getUser());
        dataSource.setPassword(getPassword());
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setPreferredTestQuery(getDatabaseValidationQuery());
        dataSource.setIdleConnectionTestPeriod(1800000);
        return dataSource;
    }

    protected Class<? extends Dialect> getDatabaseDialect() {
        return MySQL5Dialect.class;
    }

    @Bean
    protected Properties getJpaProperties() {
        return null;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabasePlatform(getDatabaseDialect().getName());
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(getEntityPackage());
        factory.setDataSource(dataSource());
        if (getJpaProperties() != null) {
            factory.setJpaProperties(getJpaProperties());
        }
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    public String getDatabaseName() {
        return getEnvironment().getProperty("database.name", UNDEFINED);
    }

    public String getHost() {
        return getEnvironment().getProperty("database.host", UNDEFINED);
    }

    public String getPort() {
        return getEnvironment().getProperty("database.port", UNDEFINED);
    }

    public String getUrl() {
        return getEnvironment().getProperty("database.url", UNDEFINED);
    }

    public String getUser() {
        return getEnvironment().getProperty("database.username", UNDEFINED);
    }

    public String getPassword() {
        return getEnvironment().getProperty("database.password", UNDEFINED);
    }

    public String getDriverClassName() {
        return getEnvironment().getProperty("database.driverClassName", UNDEFINED);
    }

    public String getEntityPackage() {
        return getEnvironment().getProperty("entity.package", entityPackage);
    }

    public String getDialect() {
        return getEnvironment().getProperty("database.dialect", UNDEFINED);
    }

    public String getDatabaseVendor() {
        return getEnvironment().getProperty("database.vendor", UNDEFINED);
    }

    public String getHbm2ddl() {
        String value = getEnvironment().getProperty("database.hbm2ddl.auto", "none");
        if (value.equals("database.hbm2ddl")) {
            value = "none";
        }
        return value;
    }

    public String getHibernateCharSet() {
        return getEnvironment().getProperty("database.hibernateCharSet", "UTF-8");
    }

    public String getDatabaseValidationQuery() {
        return getEnvironment().getProperty("database.validation.query", UNDEFINED);
    }

    /**
     * @return the environment
     */
    protected Properties getEnvironment() {
        //return environment;
        return databaseProperties;
    }

    @Bean
    public Properties databaseProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/META-INF/spring/default.properties"));
        Properties properties = null;
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();

        } catch (IOException e) {
            //log.warn("Cannot load properties file.");
        }
        return properties;
    }
}
