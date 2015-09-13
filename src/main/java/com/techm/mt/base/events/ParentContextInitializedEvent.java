package com.techm.mt.base.events;

import com.techm.mt.base.tenant.TenantContextBuilder;
import com.techm.mt.config.BaseConfig;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@Component(value = "parentContextInitializedEvent")
public class ParentContextInitializedEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        AnnotationConfigEmbeddedWebApplicationContext parent = (AnnotationConfigEmbeddedWebApplicationContext) event.getApplicationContext();

        // prevent this event from invoked again when tenant specific contexts are refreshed
        parent.removeBeanDefinition("parentContextInitializedEvent");

        TenantContextBuilder tenantContextBuilder = new TenantContextBuilder();
        tenantContextBuilder.buildTenantContext(parent);
    }
}
