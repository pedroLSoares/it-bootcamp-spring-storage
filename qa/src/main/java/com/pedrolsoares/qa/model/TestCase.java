package com.pedrolsoares.qa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Boolean tested;

    @Column
    private Boolean passed;

    @Column
    private Integer numberOfTries;

    @Column
    private final Date lastUpdate = new Date();

    public TestCase(String description, Boolean tested, Boolean passed, Integer numberOfTries) {
        this.description = description;
        this.tested = tested;
        this.passed = passed;
        this.numberOfTries = numberOfTries;
    }
}
