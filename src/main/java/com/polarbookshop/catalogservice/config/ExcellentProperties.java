package com.polarbookshop.catalogservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

//mark the class as a source for config properties starting with prefix = "polar"
//define custom properties
@ConfigurationProperties(prefix = "polar")
public class ExcellentProperties {
    private String greeting;
    private final TestData testData;

    @Autowired
    public Environment environment;

    @Value("${server.port}")
    private String serverPort;

    public ExcellentProperties() {
        this.testData = new TestData();
    }

    public TestData getTestData() {
        return this.testData;
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

    public static class TestData {
        private boolean enabled;

        public TestData() {
            this.enabled = false;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

    }
}
