package com.pedrolsoares.jewelry.dto;

import com.pedrolsoares.jewelry.model.Jewel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class JewelBodyDTO {

    @NotNull
    @NotBlank
    private String material;
    @NotNull
    private BigDecimal weight;
    @NotNull
    private Integer karat;

    public Jewel dtoToModel() {
        Jewel jewel = new Jewel();
        jewel.setMaterial(material);
        jewel.setWeight(weight);
        jewel.setKarat(karat);

        return jewel;
    }
}
