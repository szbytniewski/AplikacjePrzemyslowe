package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Cart;
import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.User;
import com.example.ecommercestore.repository.CartRepository;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartController(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long productId = request.get("productId");

        if (userId == null || productId == null) {
            throw new IllegalArgumentException("Missing userId or productId");
        }

        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return cartRepository.findByUser(user).orElse(new Cart(user));
    }
}