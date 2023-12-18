package com.AutoOrders.Storage.orders;

import com.AutoOrders.Storage.storage.Storage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

    @Query("SELECT o FROM Orders o WHERE CONCAT(o.data, ' ', o.dostawca) LIKE %?1%")
    public List<Orders> search(String keyword);
    public  Long countById(Integer id);
}
