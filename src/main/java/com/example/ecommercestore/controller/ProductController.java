package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Review;
import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.service.CartService;
import com.example.ecommercestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            Model model) {
        List<Product> products = productService.filterAndSortProducts(category, sortBy, minPrice, maxPrice);
        model.addAttribute("products", products);
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("totalCost", cartService.getTotalCost());
        return "product/list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        model.addAttribute("averageRating", productService.getAverageRating(product));
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("totalCost", cartService.getTotalCost());

        return "product/details";
    }
    @PostMapping("/{id}/reviews")
    public String addReview(@PathVariable Long id,
                            @RequestParam String username,
                            @RequestParam String comment,
                            @RequestParam int rating) {
        Review review = new Review();
        review.setUsername(username);
        review.setComment(comment);
        review.setRating(rating);
        productService.addReviewToProduct(id, review);
        return "redirect:/products/" + id;
    }
}
