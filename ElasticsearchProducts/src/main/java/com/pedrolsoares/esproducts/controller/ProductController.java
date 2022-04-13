package com.pedrolsoares.esproducts.controller;

import com.pedrolsoares.esproducts.dto.ProductDTO;
import com.pedrolsoares.esproducts.model.Product;
import com.pedrolsoares.esproducts.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/elasticsearch/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProductDTO newProduct) {
        Product created = productService.saveProduct(newProduct.dtoToModel());

        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<?> listProducts(@RequestParam String name){
        List<Product> found = productService.findAllBy(name);

        return ResponseEntity.ok(found);
    }
}
