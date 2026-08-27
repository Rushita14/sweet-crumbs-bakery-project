package com.sweetcrumbs.sweet_crumbs_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetcrumbs.sweet_crumbs_backend.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}