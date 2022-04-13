package com.pedrolsoares.esproducts.dto;

import com.pedrolsoares.esproducts.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private String category;
    private BigDecimal price;
    private Integer quantity;
    private String storage;

    public Product dtoToModel(){
        return new Product(
                name,
                category,
                price,
                quantity,
                storage
        );
    }
}
