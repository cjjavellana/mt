package com.techm.mt.tenantsupport.tenants.hk;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.techm.mt.base.database.CommonJpaConfigurations;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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


@Configuration
@ComponentScan(basePackages = {"com.techm.mt.model", "com.techm.mt.tenantsupport.tenants.hk"})
@PropertySource("classpath:/META-INF/spring/hk.properties")
@EnableJpaRepositories(basePackages = {"com.techm.mt.repositories", "com.techm.mt.tenantsupport.tenants.hk.repositories"})
@EnableTransactionManagement
public class HkMysqlJpaConfiguration extends CommonJpaConfigurations {

    @Autowired
    @Qualifier("databaseProperties")
    private Properties databaseProperties;

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

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            return getDataSource();
        } catch (PropertyVetoException pve) {
            throw new RuntimeException("Unable to create  datasource", pve);
        }
    }

    @Override
    protected Class<? extends Dialect> getDatabaseDialect() {
        return MySQL5Dialect.class;
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
        propertiesFactoryBean.setLocation(new ClassPathResource("/META-INF/spring/hk.properties"));
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
