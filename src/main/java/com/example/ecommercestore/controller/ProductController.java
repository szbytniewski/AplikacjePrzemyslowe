package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Review;
import com.example.ecommercestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/list";
    }

    @GetMapping("/products/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("averageRating", productService.getAverageRating(id));
        return "product/details";
    }
    @PostMapping("/products/{id}/reviews")
    public String addReview(@PathVariable Long id,
                            @RequestParam String username,
                            @RequestParam String comment,
                            @RequestParam int rating) {
        Review review = new Review(username, comment, rating);
        productService.addReviewToProduct(id, review);
        return "redirect:/products/" + id;
    }
}
