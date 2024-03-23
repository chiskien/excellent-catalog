package com.excellent.catalogservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

//mark the class as a source for config properties starting with prefix = "polar"
//define custom properties
@ConfigurationProperties(prefix = "polar")
public class ExcellentProperties {
    private String greeting;
    private final Environment environment;

    @Value("${server.port}")
    private String serverPort;

    public ExcellentProperties(Environment environment) {
        this.environment = environment;
    }


    public String getServerPortByEnvironment() {
        return environment.getProperty("server.port");
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
