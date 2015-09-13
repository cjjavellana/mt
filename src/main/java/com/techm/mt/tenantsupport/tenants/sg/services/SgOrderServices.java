package com.techm.mt.tenantsupport.tenants.sg.services;

import com.techm.mt.base.model.SalesOrder;
import com.techm.mt.base.repositories.SalesOrderRepository;
import com.techm.mt.base.services.InventoryService;
import com.techm.mt.base.services.OrderServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by cjavellana on 11/9/15.
 */
@Service
public class SgOrderServices extends OrderServicesImpl {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public SalesOrder addOrder() {
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setReferenceId("sgref_id");
        salesOrder.setOrderAmount(1001);
        salesOrder.setOrderDate(new Date());
        return salesOrderRepository.save(salesOrder);
    }
}
