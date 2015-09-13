package com.techm.mt.base.repositories;

import com.techm.mt.base.model.SalesOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cjavellana on 13/9/15.
 */
public interface SalesOrderRepository extends CrudRepository<SalesOrder, Integer> {

}
