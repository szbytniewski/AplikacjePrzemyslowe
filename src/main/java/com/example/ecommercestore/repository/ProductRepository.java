package com.example.ecommercestore.repository;

import com.example.ecommercestore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.title LIKE %:keyword%")
    List<Product> searchProducts(@Param("keyword") String keyword);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}