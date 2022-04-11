package com.pedrolsoares.cascadingfetchtypes.dto.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class MovieDTO {

    @NotNull
    @NotBlank
    private String title;

    @DecimalMin(value = "0.0")
    @Digits(integer = 3, fraction = 1)
    private BigDecimal rating;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer awards;

    @NotNull
    private Date releaseDate;

    @NotNull
    private Integer length;

    @NotNull
    @NotBlank
    private Long genreId;

    @NotEmpty
    private List<Long> actorsIdList;
}
