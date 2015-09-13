package com.techm.mt.tenantsupport.tenants.sg;

import com.techm.mt.base.services.OrderServices;
import com.techm.mt.base.tenant.Tenant;
import com.techm.mt.tenantsupport.tenants.sg.services.SgOrderServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class contains the Singapore tenant configuration
 */
@Configuration
public class SgTenant implements Tenant {

    @Override
    public String getName() {
        return "sg-tenant";
    }

    @Bean
    public OrderServices orderServices() {
        return new SgOrderServices();
    }
}
