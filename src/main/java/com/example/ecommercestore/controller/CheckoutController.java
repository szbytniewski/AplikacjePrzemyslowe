package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Cart;
import com.example.ecommercestore.entity.Order;
import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.User;
import com.example.ecommercestore.repository.CartRepository;
import com.example.ecommercestore.repository.OrderRepository;
import com.example.ecommercestore.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CheckoutController(OrderRepository orderRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");

        if (userId == null) {
            return ResponseEntity.badRequest().body("Missing userId");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Optional<Cart> cartOptional = cartRepository.findByUser(userOptional.get());
        if (cartOptional.isEmpty() || cartOptional.get().getProducts().isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is empty. Cannot checkout.");
        }

        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.findByUser(user).orElseThrow();

        List<Product> orderProducts = new ArrayList<>(cart.getProducts());

        double totalAmount = orderProducts.stream().mapToDouble(Product::getPrice).sum();
        Order order = new Order(user, orderProducts, totalAmount);

        cart.getProducts().clear();
        cartRepository.save(cart);


        return ResponseEntity.ok(orderRepository.save(order));
    }
}