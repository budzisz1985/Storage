package com.AutoOrders.Storage.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository repo;

    public List<Storage> listAll(String keyword) {
        if (keyword!=null){
            return repo.search(keyword);
        }
        return (List<Storage>) repo.findAll();
    }

    public void save(Storage storage) {
        repo.save(storage);
    }

    public Storage get(Integer id) throws PosNotFoundExeption {
        Optional<Storage> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
throw new PosNotFoundExeption("Nie znaleziono pozycji w magazymie");
    }

    public void delete(Integer id) throws PosNotFoundExeption {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new PosNotFoundExeption("Nie znaleziono pozycji w magazymie");
        }
        repo.deleteById(id);
    }
}
