package com.example.ecommercestore.service;

import com.example.ecommercestore.entity.Cart;
import com.example.ecommercestore.entity.CartItem;
import com.example.ecommercestore.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final Cart cart = new Cart();

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Product product, int quantity) {
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                cart.recalculateTotal();
                return;
            }
        }

        cart.getItems().add(new CartItem(product, quantity));
        cart.recalculateTotal();
    }

    public void removeFromCart(Long productId) {
        cart.removeItem(productId);
    }

    public double getTotalCost() {
        return cart.getTotalCost();
    }

    public void clearCart() {
        cart.clearCart();
    }

}
