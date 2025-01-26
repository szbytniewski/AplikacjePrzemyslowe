package com.example.ecommercestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Review {
    private String username;
    private String comment;
    private int rating;
}
