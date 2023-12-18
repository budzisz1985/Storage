package com.AutoOrders.Storage.FvDetails;

import com.AutoOrders.Storage.FvDetails.FvDetails;
import com.AutoOrders.Storage.FvDetails.FvDetailsRepository;
import com.AutoOrders.Storage.orders.Orders;
import com.AutoOrders.Storage.storage.PosNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FvDetailsService {
    @Autowired
    private FvDetailsRepository repo;

    public Iterable<FvDetails> listAll() {

        return repo.findAll();
    }
    public void save(FvDetails fvDetails) {
        repo.save(fvDetails);
    }

    public FvDetails get(Integer id) throws PosNotFoundExeption {
        Optional<FvDetails> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PosNotFoundExeption("Nie znaleziono pozycji na fakturze");
    }
}
