package com.excellent.catalogservice.controllers;


import com.excellent.catalogservice.config.ExcellentProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ExcellentProperties excellentProperties;

    public HomeController(ExcellentProperties excellentProperties) {
        this.excellentProperties = excellentProperties;
    }

    @GetMapping("/")
    public String getGreet() {
        return excellentProperties.getGreeting();
    }

}
