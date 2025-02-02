package com.example.ecommercestore.service;

import com.example.ecommercestore.entity.Product;
import com.example.ecommercestore.entity.Review;
import com.example.ecommercestore.exception.ProductNotFoundException;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService implements com.example.ecommercestore.interfaces.ProductServiceInterface {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private final List<Product> products = new ArrayList<>();
    private Long nextId = 2L;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> filterAndSortProducts(String category, String sortBy, Double minPrice, Double maxPrice) {
        return productRepository.findAll().stream()
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
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    public void addReviewToProduct(Long productId, Review review) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        review.setProduct(product);
        reviewRepository.save(review);
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
