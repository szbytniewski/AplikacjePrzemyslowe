package com.example.ecommercestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private String extendedDescription;
    private double price;
    private double paczkomatPrice;
    private double pocztaPrice;
    private double kurierPrice;
    private int stockQuantity;
    private List<Review> reviews = new ArrayList<>();
}
