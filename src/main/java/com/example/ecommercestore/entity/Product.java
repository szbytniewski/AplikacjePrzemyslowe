package com.example.ecommercestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private String extendedDescription;
    private double price;
    private double deliveryPrice;
    private int stockQuantity;
}
