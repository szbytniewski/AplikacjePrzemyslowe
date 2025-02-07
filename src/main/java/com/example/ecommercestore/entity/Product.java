package com.example.ecommercestore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 100, message = "Title should be between 3 and 100 characters")
    private String title;

    @Size(max = 255, message = "Image URL should not exceed 255 characters")
    private String imageUrl;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @DecimalMin(value = "0.0", message = "Price with shipping must be non-negative")
    private Double priceWithShipping;

    @Size(max = 255, message = "Short description should not exceed 255 characters")
    private String shortDescription;

    private String detailedDescription;

    @Min(value = 0, message = "Stock quantity must be non-negative")
    private Integer stockQuantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Review> reviews;
}