package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.service.CartService;
import com.example.ecommercestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    public String addToCart(@PathVariable Long productId,
                            @RequestParam int quantity,
                            @RequestParam String pricingOption) {
        Product product = productService.getProductById(productId);
        double finalPrice = product.getPrice();

        switch (pricingOption) {
            case "delivery1":
                finalPrice += product.getPaczkomatPrice();
                break;
            case "delivery2":
                finalPrice += product.getPocztaPrice();
                break;
            case "delivery3":
                finalPrice += product.getKurierPrice();
                break;
            default:
                break;
        }

        if (quantity < 1 || quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException("Invalid quantity selected");
        }

        cartService.addToCart(product, quantity, finalPrice);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }
}
