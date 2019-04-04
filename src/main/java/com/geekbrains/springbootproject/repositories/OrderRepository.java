package com.geekbrains.springbootproject.repositories;

import com.geekbrains.springbootproject.entities.Order;
import com.geekbrains.springbootproject.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUserId(long userId);
}
