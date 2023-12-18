package com.AutoOrders.Storage;

import com.AutoOrders.Storage.FV.FV;
import com.AutoOrders.Storage.FV.FVRepository;
import com.AutoOrders.Storage.FvDetails.FvDetails;
import com.AutoOrders.Storage.FvDetails.FvDetailsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class FVRepositoryTest {
    @Autowired
    FVRepository repo;
    @Autowired
    private FvDetailsRepository repoDetails;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testAddNew() {
        FV fv = new FV();
        fv.setDataDostawy("24.01.2023");
        fv.setNumerFV("001/2022");
        fv.setKontrahent("Makro");
        fv.setKwota("2538,37");
        fv.setTermin("24.02.2023");

        FV savedFV = repo.save(fv);

        Assertions.assertThat(savedFV).isNotNull();
        Assertions.assertThat(savedFV.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<FV> fvs = repo.findAll();
        Assertions.assertThat(fvs).hasSizeGreaterThan(0);

        for (FV fv : fvs) {
            System.out.println(fvs);
        }
    }

    @Test
    public void testUpdat(){
        Integer FVId = 1;
        Optional<FV> optionalFV = repo.findById(FVId);
        FV fv = optionalFV.get();
        fv.setKwota("2692,33");
        repo.save(fv);

        FV updateFV = repo.findById(FVId).get();
        Assertions.assertThat(updateFV.getKwota()).isEqualTo("2692,33");
    }

    @Test
    public void testGet() {
        Integer FVId = 1;
        Optional<FV> optionalFV = repo.findById(FVId);
        Assertions.assertThat(optionalFV).isPresent();
        System.out.println(optionalFV.get());
    }

    @Test
    public void testDelete() {
        Integer
                FVId = 2;
        repo.deleteById(FVId);

        Optional<FV> optionalFV = repo.findById(FVId);
        Assertions.assertThat(optionalFV).isNotPresent();
    }

    @Test
    public void testAddFvDetailsNew() {
        FvDetails fvDetails = new FvDetails();
        fvDetails.setZaplata("Gotówka");

        FvDetails savedFvDetails = repoDetails.save(fvDetails);

        Assertions.assertThat(savedFvDetails).isNotNull();
        Assertions.assertThat(savedFvDetails.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewFvWithDetail(){
        FvDetails fv1 = entityManager.find(FvDetails.class, 4);
        FV fv = new FV();
        fv.setDataDostawy("20.02.2023");
        fv.setNumerFV("002/2023");
        fv.setKontrahent("Makro");
        fv.setKwota("1555,37");
        fv.setTermin("27.02.2023");

        fv.addDetails(fv1);
        FV savedFV = repo.save(fv);
    }

    @Test
    public void testCreateNewFvWithFewDetails(){
        FvDetails fv1 = entityManager.find(FvDetails.class, 1);
        FvDetails fv2 = entityManager.find(FvDetails.class, 2);
        FV fv = new FV();
        fv.setDataDostawy("21.02.2023");
        fv.setNumerFV("003/2023");
        fv.setKontrahent("Makro");
        fv.setKwota("1894,22");
        fv.addDetails(fv1);
        fv.addDetails(fv2);
        FV savedFV = repo.save(fv);
    }

    @Test
        public void testAddDetailsToExistingFV(){
        FV fv = repo.findById(9).get();
        FvDetails fv3 = entityManager.find(FvDetails.class,4);
        FvDetails fv4 = entityManager.find(FvDetails.class, 6);
        fv.addDetails(fv3);
        fv.addDetails(fv4);
    }

    @Test
    public void testRemoveDetailFromExistingFV(){
        FV fv = repo.findById(9).get();
        FvDetails fvDetails = new FvDetails(1);
        fv.removeFvDetails(fvDetails);
    }

    @Test
    public void getFV(){
        FV fv = repo.findById(9).get();
            System.out.println(fv.getNumerFV());
            System.out.println(fv.getDetails());
    }
}