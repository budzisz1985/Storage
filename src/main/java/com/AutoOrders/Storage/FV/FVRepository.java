package com.AutoOrders.Storage.FV;

import com.AutoOrders.Storage.orders.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface FVRepository extends CrudRepository<FV, Integer> {

    public  Long countById(Integer id);

    Iterable<FV> findAll(Sort created);
}
