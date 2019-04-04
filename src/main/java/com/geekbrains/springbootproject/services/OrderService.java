package com.geekbrains.springbootproject.services;

import com.geekbrains.springbootproject.entities.Order;
import com.geekbrains.springbootproject.entities.OrderItem;
import com.geekbrains.springbootproject.entities.OrderStatus;
import com.geekbrains.springbootproject.entities.User;
import com.geekbrains.springbootproject.repositories.OrderRepository;
import com.geekbrains.springbootproject.repositories.UserRepository;
import com.geekbrains.springbootproject.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Order makeOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setId(0L);
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setPrice(cart.getTotalCost());
        order.setOrderItems(new ArrayList<>(cart.getItems()));
        for (OrderItem o : order.getOrderItems()) {
            o.setOrder(order);
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getAllOrdersByUser(User user) {
        if (user == null) return null;
        long userId = user.getId();

        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders;

    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order saveOrder(Order order) {
        Order orderOut = orderRepository.save(order);
        orderOut.setConfirmed(true);
        return orderOut;
    }

    public Order changeOrderStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);
        return saveOrder(order);
    }
}
