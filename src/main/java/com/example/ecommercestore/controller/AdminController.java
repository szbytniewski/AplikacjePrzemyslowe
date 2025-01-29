package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductServiceInterface productService;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/dashboard";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin";
    }

    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    @GetMapping("/moderate-reviews/{id}")
    public String moderateReviews(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("reviews", product.getReviews());
        return "admin/moderate-reviews";
    }

    @PostMapping("/delete-review/{productId}/{reviewIndex}")
    public String deleteReview(@PathVariable Long productId, @PathVariable int reviewIndex) {
        productService.removeReview(productId, reviewIndex);
        return "redirect:/admin/moderate-reviews/" + productId;
    }
}
