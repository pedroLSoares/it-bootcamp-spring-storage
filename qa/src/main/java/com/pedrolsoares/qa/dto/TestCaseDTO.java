package com.pedrolsoares.qa.dto;

import com.pedrolsoares.qa.model.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TestCaseDTO {

    private String description;
    private Boolean tested;
    private Boolean passed;
    private Integer numberOfTries;

    public TestCase dtoToModel() {
        return new TestCase(
                description,
                tested,
                passed,
                numberOfTries
        );
    }

    public TestCase dtoToModel(Long id) {
        return new TestCase(
                id,
                description,
                tested,
                passed,
                numberOfTries
        );
    }
}
