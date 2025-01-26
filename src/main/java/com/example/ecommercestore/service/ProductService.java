package com.example.ecommercestore.service;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Laptop", "/images/laptop.jpg", "Opis laptopa", "Szczegółowy opis laptopa", 3000.00, 3020.00,3040.00,3060.00, 10, new ArrayList<>()));
        products.add(new Product(2L, "Telefon", "/images/phone.jpg", "Opis telefonu", "Szczegółowy opis telefonu", 2000.00, 2020.00,2040.00,2060.00, 15, new ArrayList<>()));
        products.add(new Product(3L, "Smartwatch", "/images/smartwatch.jpg", "Opis smartwatcha", "Szczegółowy opis smartwatcha", 800.00, 820.00,840.00,860.00, 20, new ArrayList<>()));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void addReviewToProduct(Long productId, Review review) {
        Product product = getProductById(productId);
        product.getReviews().add(review);
    }

    public double getAverageRating(Long productId) {
        Product product = getProductById(productId);
        return product.getReviews().stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
