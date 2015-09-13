package com.techm.mt.base.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by cjavellana on 13/9/15.
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TenantServiceImpl implements TenantService {

    @Override
    public String getTenantSchemaName() {
        return null;
    }

    @Override
    public void setTenantSchemaName(String name) {

    }
}
