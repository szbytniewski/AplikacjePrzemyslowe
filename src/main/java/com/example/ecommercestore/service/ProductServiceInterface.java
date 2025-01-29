package com.example.ecommercestore.service;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.Review;
import com.example.ecommercestore.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceInterface implements com.example.ecommercestore.interfaces.ProductServiceInterface {

    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public ProductServiceInterface() {
        products.add(new Product(1L, "Laptop", "/images/laptop.jpg", "Opis laptopa", "Szczegółowy opis laptopa", 3000.00, 3020.00,3040.00,3060.00, 10, new ArrayList<>()));
        products.add(new Product(2L, "Telefon", "/images/phone.jpg", "Opis telefonu", "Szczegółowy opis telefonu", 2000.00, 2020.00,2040.00,2060.00, 15, new ArrayList<>()));
        products.add(new Product(3L, "Smartwatch", "/images/smartwatch.jpg", "Opis smartwatcha", "Szczegółowy opis smartwatcha", 800.00, 820.00,840.00,860.00, 20, new ArrayList<>()));

        nextId = 4L;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public List<Product> filterAndSortProducts(String category, String sortBy, Double minPrice, Double maxPrice) {
        return products.stream()
                .filter(product -> category == null || product.getTitle().toLowerCase().contains(category.toLowerCase()))
                .filter(product -> minPrice == null || product.getPrice() >= minPrice)
                .filter(product -> maxPrice == null || product.getPrice() <= maxPrice)
                .sorted(getComparator(sortBy))
                .collect(Collectors.toList());
    }

    private Comparator<Product> getComparator(String sortBy) {
        if ("priceAsc".equals(sortBy)) {
            return Comparator.comparing(Product::getPrice);
        } else if ("priceDesc".equals(sortBy)) {
            return Comparator.comparing(Product::getPrice).reversed();
        } else if ("rating".equals(sortBy)) {
            return Comparator.comparing(this::getAverageRating).reversed();
        }
        return Comparator.comparing(Product::getId);
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    public void addReviewToProduct(Long productId, Review review) {
        Product product = getProductById(productId);
        product.getReviews().add(review);
    }

    public double getAverageRating(Product product) {
        return product.getReviews().stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    public void saveProduct(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.add(product);
    }

    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public void removeReview(Long productId, int reviewIndex) {
        Product product = getProductById(productId);
        if (reviewIndex >= 0 && reviewIndex < product.getReviews().size()) {
            product.getReviews().remove(reviewIndex);
        }
    }
}
