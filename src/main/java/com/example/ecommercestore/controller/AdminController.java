package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.service.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EcommerceService ecommerceService;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("products", ecommerceService.getAllProducts());
        return "admin/dashboard";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        ecommerceService.addProduct(product);
        return "redirect:/admin";
    }

    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        ecommerceService.deleteProduct(id);
        return "redirect:/admin";
    }

    @GetMapping("/moderate-reviews/{id}")
    public String moderateReviews(@PathVariable Long id, Model model) {
        Product product = ecommerceService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("reviews", product != null ? product.getReviews() : null);
        return "admin/moderate-reviews";
    }

    @PostMapping("/delete-review/{productId}/{reviewIndex}")
    public String deleteReview(@PathVariable Long productId, @PathVariable int reviewIndex) {
        return "redirect:/admin/moderate-reviews/" + productId;
    }
}