package com.pedrolsoares.esarticles.dto;

import com.pedrolsoares.esarticles.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private String name;

    public Author dtoToModel(){
        return new Author(name);
    }
}
