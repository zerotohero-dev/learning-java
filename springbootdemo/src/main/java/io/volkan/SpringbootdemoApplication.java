package io.volkan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringbootdemoApplication {

    public static void main(String[] args) {

        // Instead of grabbing the application context and getting beans out of it and executing them,
        // SpringApplication.run run will will load the class with any command-line arguments and execute
        // the application
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }
}
