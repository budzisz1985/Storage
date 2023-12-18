package com.AutoOrders.Storage.FvDetails;

import com.AutoOrders.Storage.FvDetails.FvDetails;
import com.AutoOrders.Storage.orders.Orders;
import org.springframework.data.repository.CrudRepository;

public interface FvDetailsRepository extends CrudRepository<FvDetails, Integer> {

    public  Long countById(Integer id);
}
