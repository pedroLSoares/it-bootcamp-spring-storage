package com.pedrolsoares.esarticles.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {

    private String name;

    public Author(String name) {
        this.name = name;
    }
}
