package com.pedrolsoares.cascadingfetchtypes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CascadingFetchtypesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CascadingFetchtypesApplication.class, args);
    }

}
