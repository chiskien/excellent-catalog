package com.excellentbookshop.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PolarBookshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(PolarBookshopApplication.class, args);
    }

}
