package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.service.CartService;
import com.example.ecommercestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("totalCost", cartService.getTotalCost());
        return "cart/view";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam int quantity) {
        Product product = productService.getProductById(productId);

        if (quantity < 1 || quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException("Invalid quantity selected");
        }

        cartService.addToCart(product, quantity);
        return "redirect:/products/" + productId;
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }
}
