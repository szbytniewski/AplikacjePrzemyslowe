package com.example.ecommercestore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String comment;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
