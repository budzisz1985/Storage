package com.AutoOrders.Storage.orders;

import com.AutoOrders.Storage.storage.PosNotFoundExeption;
import com.AutoOrders.Storage.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired private OrdersRepository repo;

    public List<Orders> listAll(String keyword) {
        if (keyword!=null){
            return repo.search(keyword);
        }
        return (List<Orders>) repo.findAll();
    }

    public void save(Orders orders) {
        repo.save(orders);
    }

    public Orders get(Integer id) throws PosNotFoundExeption {
        Optional<Orders> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PosNotFoundExeption("Nie znaleziono pozycji w zamówieniu");
    }

    public void delete(Integer id) throws PosNotFoundExeption {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new PosNotFoundExeption("Nie znaleziono pozycji w magazymie");
        }
        repo.deleteById(id);
    }
}
