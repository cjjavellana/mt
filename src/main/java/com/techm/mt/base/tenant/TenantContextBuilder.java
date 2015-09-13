package com.techm.mt.base.tenant;

import com.techm.mt.tenantsupport.tenants.hk.HkMysqlJpaConfiguration;
import com.techm.mt.tenantsupport.tenants.hk.HkTenant;
import com.techm.mt.tenantsupport.tenants.sg.SgMysqlJpaConfiguration;
import com.techm.mt.tenantsupport.tenants.sg.SgTenant;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TenantContextBuilder {

    public void buildTenantContext(AnnotationConfigEmbeddedWebApplicationContext parentApplicationContext) {
        AnnotationConfigApplicationContext hkAppContext = new AnnotationConfigApplicationContext();
        // allow tenant context access to parent base context
        hkAppContext.setParent(parentApplicationContext);
        hkAppContext.register(HkTenant.class);
        hkAppContext.register(HkMysqlJpaConfiguration.class);
        hkAppContext.refresh();
        TenantContextHolder.addTenantContext(HkTenant.class.getSimpleName(), hkAppContext);

        AnnotationConfigApplicationContext sgAppContext = new AnnotationConfigApplicationContext();
        sgAppContext.setParent(parentApplicationContext);
        sgAppContext.register(SgTenant.class);
        sgAppContext.register(SgMysqlJpaConfiguration.class);
        sgAppContext.refresh();
        TenantContextHolder.addTenantContext(SgTenant.class.getSimpleName(), sgAppContext);

    }

}
