package com.techm.mt.tenantsupport.tenants.hk;

import com.techm.mt.base.services.OrderServices;
import com.techm.mt.base.tenant.Tenant;
import com.techm.mt.tenantsupport.tenants.hk.services.HkOrderServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class contains the Hongkong tenant configuration
 */
@Configuration
public class HkTenant implements Tenant {

    @Override
    public String getName() {
        return "hk-tenant";
    }

    @Bean
    public OrderServices orderServices() {
        return new HkOrderServices();
    }
}
