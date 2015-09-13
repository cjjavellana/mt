package com.techm.mt.base.tenant;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Maintains a map of tenant's unique name to the tenants application context
 */
public class TenantContextHolder {

    private static Map<String, ApplicationContext> tenantContextMap = new HashMap<>();

    public static void addTenantContext(String uniqueName, ApplicationContext tenantAppContext) {
        tenantContextMap.put(uniqueName, tenantAppContext);
    }

    /**
     * This method returns null if tenant context is not found
     *
     * @param uniqueName
     * @return
     */
    public static ApplicationContext getTenantContext(String uniqueName) {
        return tenantContextMap.get(uniqueName);
    }

    public static boolean isUninitialized() {
        return tenantContextMap.isEmpty();
    }
}
