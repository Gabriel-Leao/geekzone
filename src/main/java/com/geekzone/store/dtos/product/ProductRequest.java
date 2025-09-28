package com.geekzone.store.dtos.product;

import com.geekzone.store.validations.productCategory.ValidCategory;
import com.geekzone.store.validations.releaseDate.ValidReleaseDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private Integer quantity;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @ValidReleaseDate
    @NotBlank(message = "Release date is required")
    private String releaseDate;

    @ValidCategory
    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "BrandID is required")
    private long brandID;
}
