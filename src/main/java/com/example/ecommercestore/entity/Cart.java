package com.example.ecommercestore.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity, double finalPrice) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId()) && item.getFinalPrice() == finalPrice) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity, finalPrice));
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void clearCart() {
        items.clear();
    }
}
