package com.pedrolsoares.hql.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SerieDTO {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private Long genreId;

    @NotNull
    @NotBlank
    private LocalDateTime releaseDate;

    private LocalDateTime endDate;
}
