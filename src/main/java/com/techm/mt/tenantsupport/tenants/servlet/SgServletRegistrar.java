package com.techm.mt.tenantsupport.tenants.servlet;

import com.techm.mt.config.BaseConfig;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class SgServletRegistrar {

    /**
     * This is the base dispatcher servlet. Tenant specific dispatcher servlet has to be declared (and included) in a tenant specific library
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean sgDispatcherServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigEmbeddedWebApplicationContext applicationContext = new AnnotationConfigEmbeddedWebApplicationContext();
        applicationContext.register(BaseConfig.class);
        dispatcherServlet.setApplicationContext(applicationContext);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/cosys/sg/v1");
        servletRegistrationBean.setName("cosys-sg-v1");
        return servletRegistrationBean;
    }

}
