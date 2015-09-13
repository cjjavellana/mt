package com.techm.mt.base.listeners;

import com.techm.mt.base.services.TenantService;
import com.techm.mt.base.tenant.TenantContextHolder;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cjavellana on 12/9/15.
 */
public class TenantRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(final ServletRequestEvent servletRequestEvent) {
    }

    @Override
    public void requestInitialized(final ServletRequestEvent servletRequestEvent) {
        final HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        final String path = request.getServletPath();
        final ApplicationContext applicationContext = TenantContextHolder.getTenantContext(path);
        TenantService tenantService = (TenantService) applicationContext.getBean(TenantService.class);
        tenantService.setTenantSchemaName(path);
    }
}
