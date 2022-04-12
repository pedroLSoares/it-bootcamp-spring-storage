package com.pedrolsoares.hql.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ActorDTO {

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @DecimalMin(value = "0.0")
    @Digits(integer = 3, fraction = 1)
    private BigDecimal rating;

    private Long favoriteMovieId;

}
