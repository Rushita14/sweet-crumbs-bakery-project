package com.sweetcrumbs.sweet_crumbs_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetcrumbs.sweet_crumbs_backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}