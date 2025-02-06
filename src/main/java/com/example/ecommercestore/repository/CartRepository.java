package com.example.ecommercestore.repository;

import com.example.ecommercestore.entity.Cart;
import com.example.ecommercestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}