package com.techm.mt.base.controllers;

import com.techm.mt.base.model.Message;
import com.techm.mt.base.model.SalesOrder;
import com.techm.mt.base.services.OrderServices;
import com.techm.mt.base.tenant.TenantContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@RestController
public class SalesOrderController {

    @RequestMapping(value = "/cosys/v1/addorder/{tenant}", method = RequestMethod.GET)
    public HttpEntity<Message> addOrder(@PathVariable("tenant") String tenant) {
        ApplicationContext appContext = TenantContextHolder.getTenantContext(tenant);
        OrderServices orderServices = (OrderServices) appContext.getBean("orderServices");
        SalesOrder salesOrder = orderServices.addOrder();

        Message message = new Message();
        message.setMessage("Added Order ref : " + salesOrder.getReferenceId());
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

}
