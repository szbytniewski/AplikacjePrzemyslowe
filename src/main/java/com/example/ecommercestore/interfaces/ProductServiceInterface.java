package com.example.ecommercestore.interfaces;

import com.example.ecommercestore.entity.Product;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void saveProduct(Product product);
    void deleteProduct(Long id);
}
