package com.techm.mt.base.services;

import org.springframework.stereotype.Service;

/**
 * Created by cjavellana on 11/9/15.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Override
    public void addInventory() {
        System.out.println("addInventory from InventoryServiceImpl");
    }
}
