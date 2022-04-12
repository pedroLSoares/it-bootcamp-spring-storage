package com.pedrolsoares.hql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(HqlApplication.class, args);
    }

}
