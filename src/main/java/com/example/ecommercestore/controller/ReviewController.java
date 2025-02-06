package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.Review;
import com.example.ecommercestore.entity.User;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.repository.ReviewRepository;
import com.example.ecommercestore.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewController(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody Map<String, Object> request) {
        Long userId = ((Number) request.get("userId")).longValue();
        Long productId = ((Number) request.get("productId")).longValue();
        Integer rating = ((Number) request.get("rating")).intValue();
        String comment = (String) request.get("comment");

        if (userId == null || productId == null || rating == null || comment == null) {
            return ResponseEntity.badRequest().body("Missing required fields: userId, productId, rating, or comment");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Product not found");
        }

        User user = userOptional.get();
        Product product = productOptional.get();

        Review review = new Review(null, user, product, rating, comment);
        return ResponseEntity.ok(reviewRepository.save(review));
    }

    @GetMapping("/{productId}")
    public List<Review> getReviews(@PathVariable Long productId) {
        return reviewRepository.findAll();
    }
}