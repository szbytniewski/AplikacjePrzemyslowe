package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Cart;
import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.User;
import com.example.ecommercestore.repository.CartRepository;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


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

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public Cart addToCart(@RequestBody Map<String, Long> request, Authentication authentication) {
        Long productId = request.get("productId");

        if (productId == null) {
            throw new IllegalArgumentException("Missing productId");
        }

        // Get the authenticated user's details
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Find the user's cart or create a new one
        Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
        cart.getProducts().add(product);

        return cartRepository.save(cart);
    }

    @PostMapping("/remove")
    public Cart removeFromCart(@RequestBody Map<String, Long> request, Authentication authentication) {
        Long productId = request.get("productId");
        if (productId == null) {
            throw new IllegalArgumentException("Missing productId");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        cart.getProducts().remove(product);
        return cartRepository.save(cart);
    }


    @GetMapping
    public Cart getCart(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return cartRepository.findByUser(user).orElse(new Cart(user));
    }
}