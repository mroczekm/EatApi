package com.eatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.eatapi.repository")
@SpringBootApplication
public class EatapiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EatapiApplication.class, args);
    }

}
