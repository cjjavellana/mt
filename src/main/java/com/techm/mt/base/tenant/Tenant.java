package com.techm.mt.base.tenant;

/**
 * Tenant configuration must inherit from this interface and return a uniq tenant name
 */
public interface Tenant {

    /**
     * @return
     */
    public String getName();
}
