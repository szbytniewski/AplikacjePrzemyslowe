package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        List<Product> featuredProducts = allProducts.size() > 2
                ? allProducts.subList(0, 2)
                : allProducts;

        model.addAttribute("products", featuredProducts);
        return "home";
    }
}
