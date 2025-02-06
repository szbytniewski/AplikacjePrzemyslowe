package com.example.ecommercestore.repository;

import com.example.ecommercestore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}