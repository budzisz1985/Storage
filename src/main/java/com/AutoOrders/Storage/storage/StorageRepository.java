package com.AutoOrders.Storage.storage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StorageRepository extends CrudRepository<Storage, Integer> {

    @Query("SELECT s FROM Storage s WHERE CONCAT(s.kod, ' ', s.nazwa) LIKE %?1%")
    public List<Storage> search(String keyword);
    public  Long countById(Integer id);


}
