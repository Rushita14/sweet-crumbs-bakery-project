package com.sweetcrumbs.sweet_crumbs_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sweetcrumbs.sweet_crumbs_backend.entity.Product;
import com.sweetcrumbs.sweet_crumbs_backend.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Add product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> addProducts(List<Product> products) {
    return productRepository.saveAll(products);
}

    // Update product
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImage(product.getImage());

            return productRepository.save(existingProduct);
        }

        return null;
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}