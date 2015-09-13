package com.techm.mt.base.services;

import com.techm.mt.base.model.SalesOrder;
import org.springframework.stereotype.Service;

/**
 * Created by cjavellana on 11/9/15.
 */
@Service
public class OrderServicesImpl implements OrderServices {

    @Override
    public SalesOrder addOrder() {
        System.out.println("addOrder from OrderServicesImpl");
        return new SalesOrder();
    }
}
