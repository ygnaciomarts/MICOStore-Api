package org.mico.micostoreapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String imageUrl;
    private LocalDateTime createdAt;
    private String category;

    // Constructor
    public ProductDTO(Integer id, String name, String description, BigDecimal price, Integer stock, String imageUrl, LocalDateTime createdAt, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.category = category;
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public Integer getStock() { return stock; }
    public String getImageUrl() { return imageUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getCategory() { return category; }
}