package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductServiceInterface productService;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts().subList(0, 2);
        model.addAttribute("products", products);
        return "home";
    }
}
