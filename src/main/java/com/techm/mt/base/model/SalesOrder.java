package com.techm.mt.base.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cjavellana on 12/9/15.
 */
@Entity(name = "sales_order")
public class SalesOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sales_order_id", nullable = false)
    private Integer salesOrderId;

    @Column(name = "reference_id", nullable = false)
    private String referenceId;

    @Column(name = "order_amount", nullable = false)
    private Integer orderAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
