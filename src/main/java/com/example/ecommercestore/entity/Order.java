package com.example.ecommercestore.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Product> products;

    private Double totalAmount;

    public Order(User user, List<Product> products, double totalAmount) {
        this.user = user;
        this.products = products;
        this.totalAmount = totalAmount;
    }
}