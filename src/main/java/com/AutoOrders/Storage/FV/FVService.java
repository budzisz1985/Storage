package com.AutoOrders.Storage.FV;

import com.AutoOrders.Storage.orders.Orders;
import com.AutoOrders.Storage.storage.PosNotFoundExeption;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FVService {
    @Autowired
    private FVRepository repo;

    public Iterable<FV> listAll() {


        return repo.findAll( Sort.by(Sort.Order.desc("dataDostawy")));
    }

    public void save(FV fv) {
        repo.save(fv);
    }

    public FV get(Integer id) throws PosNotFoundExeption {
        Optional<FV> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PosNotFoundExeption("Nie znaleziono fakury");
    }
}
