package com.pedrolsoares.hql.dto.request;

import com.pedrolsoares.hql.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class GenreDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer ranking;

    public Genre dtoToModel(){
        return new Genre(name, ranking);
    }

}
