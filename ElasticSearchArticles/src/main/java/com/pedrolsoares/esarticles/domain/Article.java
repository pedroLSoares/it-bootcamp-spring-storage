package com.pedrolsoares.esarticles.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


@Document(indexName = "blog")
@Getter
@Setter
@NoArgsConstructor
public class Article {

    @Id
    private String id;

    private String title;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authorList;

    public Article(String title, List<Author> authorList) {
        this.title = title;
        this.authorList = authorList;
    }
}
