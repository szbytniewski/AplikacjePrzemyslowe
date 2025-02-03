package com.example.ecommercestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CartItem {
    private Product product;
    private int quantity;
    @Getter
    private double finalPrice;

    public CartItem(Product product, int quantity, double finalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }


}
