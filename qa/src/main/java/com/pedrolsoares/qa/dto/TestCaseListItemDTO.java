package com.pedrolsoares.qa.dto;


import com.pedrolsoares.qa.model.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@AllArgsConstructor
@Data
public class TestCaseListItemDTO {

    private Long id;
    private String description;
    private Boolean tested;
    private Boolean passed;
    private Integer numberOfTries;
    private Date lastUpdate;
    private URI uri;

    public static TestCaseListItemDTO modelToDTO(TestCase testCase, UriComponentsBuilder uriBuilder) {
        UriComponentsBuilder uriBuilderComponent = uriBuilder.replaceQuery(null).replacePath("/api/v1/neighborhood/{id}");

        URI uri = uriBuilderComponent.build(testCase.getId());

        return new TestCaseListItemDTO(
                testCase.getId(),
                testCase.getDescription(),
                testCase.getTested(),
                testCase.getPassed(),
                testCase.getNumberOfTries(),
                testCase.getLastUpdate(),
                uri
        );
    }


}
