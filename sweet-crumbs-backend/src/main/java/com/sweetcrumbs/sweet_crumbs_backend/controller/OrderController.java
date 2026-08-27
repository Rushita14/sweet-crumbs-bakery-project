package com.sweetcrumbs.sweet_crumbs_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweetcrumbs.sweet_crumbs_backend.entity.Order;
import com.sweetcrumbs.sweet_crumbs_backend.entity.OrderItem;
import com.sweetcrumbs.sweet_crumbs_backend.repository.OrderItemRepository;
import com.sweetcrumbs.sweet_crumbs_backend.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderController(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        order.setStatus("PLACED");

        List<OrderItem> items = order.getItems();

        order.setItems(null);

        Order savedOrder =
                orderRepository.save(order);


        if (items != null) {

            for (OrderItem item : items) {

                item.setOrderId(
                    savedOrder.getId()
                );

                orderItemRepository.save(item);
            }

        }


        savedOrder.setItems(items);

        return savedOrder;
    }


    @GetMapping
    public List<Order> getOrders() {

        return orderRepository.findAll();
    }


    @GetMapping("/{id}/items")
    public List<OrderItem> getOrderItems(
            @PathVariable Long id) {

        return orderItemRepository
                .findByOrderId(id);
    }
}