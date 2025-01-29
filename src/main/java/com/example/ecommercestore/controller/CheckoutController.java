package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.Cart;
import com.example.ecommercestore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String checkoutForm(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "checkout/form";
    }

    @PostMapping
    public String processCheckout(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String deliveryMethod,
            Model model) {

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            model.addAttribute("error", "Invalid email format");
            model.addAttribute("cart", cartService.getCart());
            return "checkout/form";
        }

        model.addAttribute("fullName", fullName);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        model.addAttribute("deliveryMethod", deliveryMethod);
        model.addAttribute("totalCost", cartService.getTotalCost());

        cartService.clearCart();

        return "checkout/confirmation";
    }
}
