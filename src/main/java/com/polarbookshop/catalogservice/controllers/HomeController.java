package com.polarbookshop.catalogservice.controllers;


import com.polarbookshop.catalogservice.config.ExcellentProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ExcellentProperties excellentProperties;

    public HomeController(ExcellentProperties excellentProperties) {
        this.excellentProperties = excellentProperties;
    }

    @GetMapping("/greet")
    public String getGreet() {
        return excellentProperties.getGreeting();
    }

    @GetMapping("/server")
    public String getPort1() {
        return excellentProperties.getServerPort();
    }

    @GetMapping("/port")
    public String getPort2() {
        return excellentProperties.getServerPortByEnvironment();
    }
}
