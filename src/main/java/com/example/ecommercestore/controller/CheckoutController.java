package com.example.ecommercestore.controller;

import com.example.ecommercestore.entity.*;
import com.example.ecommercestore.service.CartService;
import com.example.ecommercestore.repository.OrderRepository;
import com.example.ecommercestore.repository.OrderProductRepository;
import com.example.ecommercestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

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

        // Create new order
        Order order = new Order();
        order.setCustomerName(fullName);
        order.setCustomerEmail(email);
        order.setTotalPrice(cartService.getTotalCost());

        // Save order first to generate ID
        order = orderRepository.save(order);

        // Retrieve products from cart and save as OrderProduct
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (CartItem cartItem : cartService.getCart().getItems()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cartItem.getProduct());
            orderProduct.setQuantity(cartItem.getQuantity());
            orderProducts.add(orderProduct);
        }

        // Save all OrderProduct entities
        orderProductRepository.saveAll(orderProducts);

        // Update the order with saved products and save it again
        order.setOrderProducts(orderProducts);
        orderRepository.save(order);

        // Clear cart after order submission
        cartService.clearCart();

        // Pass order details to the confirmation page
        model.addAttribute("fullName", fullName);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        model.addAttribute("deliveryMethod", deliveryMethod);
        model.addAttribute("totalCost", order.getTotalPrice());

        return "checkout/confirmation";
    }
}
