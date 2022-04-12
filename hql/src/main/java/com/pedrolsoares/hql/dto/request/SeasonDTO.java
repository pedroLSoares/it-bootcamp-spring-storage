package com.pedrolsoares.hql.dto.request;

import com.pedrolsoares.hql.model.Episode;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class SeasonDTO {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    @Min(1)
    private Integer number;

    @NotNull
    @NotBlank
    private Date releaseDate;

    private Date endDate;

    private List<EpisodeDTO> episodes;
}
