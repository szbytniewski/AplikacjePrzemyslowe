package com.example.ecommercestore.controller;

import com.example.ecommercestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return "product/details";
    }
}
