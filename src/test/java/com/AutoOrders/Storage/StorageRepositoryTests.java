package com.AutoOrders.Storage;

import com.AutoOrders.Storage.storage.Storage;
import com.AutoOrders.Storage.storage.StorageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StorageRepositoryTests {
    @Autowired StorageRepository repo;

    @Test
    public void testAddNew() {
        Storage storage = new Storage();
        storage.setKod(String.valueOf(112));
        storage.setNazwa("Schab z wedzarni");
        storage.setIloscMagazyn(1.535);
        storage.setCenaZakupu(22.30);
        storage.setCenaSprzedazy(storage.getCenaZakupu()+storage.getCenaZakupu()*0.4);
        storage.setMarza(40);
        storage.setDataWaznosci("31.12.2022");

        Storage savedStorage = repo.save(storage);

        Assertions.assertThat(savedStorage).isNotNull();
        Assertions.assertThat(savedStorage.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Storage> storages = repo.findAll();
        Assertions.assertThat(storages).hasSizeGreaterThan(0);

        for (Storage storage : storages) {
            System.out.println(storage);
        }
    }

    @Test
    public void testUpdat(){
        Integer storageId = 31;
        Optional<Storage> optionalStorage = repo.findById(storageId);
        Storage storage = optionalStorage.get();
        storage.setCenaZakupu(28.7);
        repo.save(storage);

        Storage updateStorage = repo.findById(storageId).get();
        Assertions.assertThat(updateStorage.getCenaZakupu()).isEqualTo(28.7);
    }

    @Test
    public void testGet() {
        Integer storageId = 32;
        Optional<Storage> optionalStorage = repo.findById(storageId);
        Assertions.assertThat(optionalStorage).isPresent();
        System.out.println(optionalStorage.get());
    }

    @Test
    public void testDelete() {
        Integer storageId = 32;
        repo.deleteById(storageId);

        Optional<Storage> optionalStorage = repo.findById(storageId);
        Assertions.assertThat(optionalStorage).isNotPresent();
    }

    @Test
    public void testSearch(){
        String nazwaTest = ("Schab");
        repo.search(nazwaTest);

        List<Storage> optionalStorage = repo.search(nazwaTest);
        Assertions.assertThat(optionalStorage).isNotNull();
    }


}


