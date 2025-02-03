package com.example.ecommercestore.service;

import com.example.ecommercestore.entity.Cart;
import com.example.ecommercestore.entity.CartItem;
import com.example.ecommercestore.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {
    private final Cart cart = new Cart();

    public Cart getCart() {
        return cart;
    }

    public void removeFromCart(Long productId) {
        cart.removeItem(productId);
    }

    public double getTotalCost() {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getFinalPrice() * item.getQuantity()) // Return double directly
                .sum();
    }

    public void clearCart() {
        cart.clearCart();
    }

    public void addToCart(Product product, int quantity, double finalPrice) {
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()) && item.getFinalPrice() == finalPrice)
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(product, quantity, finalPrice);
            cart.getItems().add(newItem);
        }
    }
}
