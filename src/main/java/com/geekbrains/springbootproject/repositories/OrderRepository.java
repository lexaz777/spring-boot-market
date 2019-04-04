package com.geekbrains.springbootproject.repositories;

import com.geekbrains.springbootproject.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
