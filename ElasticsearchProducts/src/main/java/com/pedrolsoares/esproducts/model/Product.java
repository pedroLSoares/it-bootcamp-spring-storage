package com.pedrolsoares.esproducts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer quantity;
    private String storage; // Change to another model

    public Product(String name, String category, BigDecimal price, Integer quantity, String storage) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.storage = storage;
    }
}
