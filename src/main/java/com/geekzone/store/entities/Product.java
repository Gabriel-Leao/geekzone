package com.geekzone.store.entities;

import com.geekzone.store.dtos.product.ProductRequest;
import com.geekzone.store.models.Categories;
import com.geekzone.store.utils.DateFormatterUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "image_url", nullable = false)
    private String image;

    @Column(nullable = false)
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Product(ProductRequest productRequestData) {
        this.name = productRequestData.getName();
        this.description = productRequestData.getDescription();
        this.price = productRequestData.getPrice();
        this.quantity = productRequestData.getQuantity();
        this.image = productRequestData.getImageUrl();
        setCategory(productRequestData.getCategory());
        setReleaseDate(productRequestData.getReleaseDate());
    }

    private Categories transformStringToCategory(String category) {
        return Categories.valueOf(category.toUpperCase());
    }

    public void setCategory(String category) {
        this.category = transformStringToCategory(category);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = DateFormatterUtil.transformStringToLocalDate(releaseDate);
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
