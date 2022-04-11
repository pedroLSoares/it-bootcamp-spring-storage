package com.pedrolsoares.cascadingfetchtypes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
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
