package com.pedrolsoares.esarticles.dto;

import com.pedrolsoares.esarticles.domain.Article;
import com.pedrolsoares.esarticles.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class ArticlesDTO {

    private String title;
    private List<AuthorDTO> authorList;

    public Article dtoToModel(){
        List<Author> authors = authorList.stream().map(AuthorDTO::dtoToModel).collect(Collectors.toList());
        return new Article(
                title,
                authors
        );
    }
}
