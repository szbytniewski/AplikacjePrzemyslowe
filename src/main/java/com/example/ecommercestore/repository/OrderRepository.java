package com.example.ecommercestore.repository;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}