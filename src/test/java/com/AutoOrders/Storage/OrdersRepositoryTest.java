package com.AutoOrders.Storage;

import com.AutoOrders.Storage.orders.Orders;
import com.AutoOrders.Storage.orders.OrdersRepository;
import com.AutoOrders.Storage.storage.Storage;
import org.assertj.core.api.Assertions;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrdersRepositoryTest {
    @Autowired OrdersRepository repo;

    @Test
    public void testAddNew(){
        Orders orders = new Orders();
        orders.setData(String.valueOf(LocalDate.now()));
        orders.setDostawca("mięso");
        orders.setNazwa("Schab polski");

        Orders savedOrders = repo.save(orders);

        Assertions.assertThat(savedOrders).isNotNull();
        Assertions.assertThat(savedOrders.getId()).isGreaterThan(0);
    }


    @Test
    public void testListAll() {
        Iterable<Orders> order = repo.findAll();
        Assertions.assertThat(order).hasSizeGreaterThan(0);

        for (Orders orders : order) {
            System.out.println(orders);
        }
    }

    @Test
    public void testUpdat(){
        Integer orderId = 5;
        Optional<Orders> optionalOrders = repo.findById(orderId);
        Orders orders = optionalOrders.get();
        orders.setNazwa("Szynka z beczki");
        repo.save(orders);

        Orders updateOrders = repo.findById(orderId).get();
        Assertions.assertThat(updateOrders.getNazwa()).isEqualTo("Szynka z beczki");
    }

    @Test
    public void testGet() {
        Integer orderId = 1;
        Optional<Orders> optionalOrders = repo.findById(orderId);
        Assertions.assertThat(optionalOrders).isPresent();
        System.out.println(optionalOrders.get());
    }

    @Test
    public void testDelete() {
        Integer orderId = 1;
        repo.deleteById(orderId);

        Optional<Orders> optionalOrders = repo.findById(orderId);
        Assertions.assertThat(optionalOrders).isNotPresent();
    }
    }
