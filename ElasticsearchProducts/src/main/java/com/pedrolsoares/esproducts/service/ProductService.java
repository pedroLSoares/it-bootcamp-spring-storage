package com.pedrolsoares.esproducts.service;

import com.pedrolsoares.esproducts.model.Product;
import com.pedrolsoares.esproducts.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.hibernate.PropertyNotFoundException;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;

import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ElasticsearchRestTemplate template;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> listProducts(){
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach(result::add);

        return result;
    }

    public Product find(String id){


        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new PropertyNotFoundException("Product " + id + " not found!");
        }

        return optionalProduct.get();
    }

    public List<Product> findAllBy(String param){
        MultiMatchQueryBuilder query = new MultiMatchQueryBuilder(param, "name", "category")
                .operator(Operator.OR)
                .type(MultiMatchQueryBuilder.Type.PHRASE);


        NativeSearchQuery result = new NativeSearchQueryBuilder().withQuery(query).build();

        SearchHits<Product> products = template.search(result, Product.class, IndexCoordinates.of("products"));

        System.out.println(products);

        return products.stream().map(SearchHit::getContent).collect(Collectors.toList());

    }
}
