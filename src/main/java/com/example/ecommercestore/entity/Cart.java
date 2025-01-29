package com.example.ecommercestore.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items = new ArrayList<>();
    private double totalCost;

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                recalculateTotal();
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        recalculateTotal();
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
        recalculateTotal();
    }

    public void recalculateTotal() {
        totalCost = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void clearCart() {
        items.clear();
        totalCost = 0.0;
    }

}
