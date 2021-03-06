package com.pedrolsoares.hql.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private Date releaseDate;

    private Date endDate;

    private List<SeasonDTO> seasons;
}
