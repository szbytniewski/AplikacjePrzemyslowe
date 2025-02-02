package com.example.ecommercestore.service;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.Order;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EcommerceService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}