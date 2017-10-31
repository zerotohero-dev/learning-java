package io.volkan.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // indicates that our class will include several bean methods
               // that will be used to create beans that will be put into our IoC Container.
public class MyConfiguration {

    @Bean
    public String message() {
        return "Hello Spring";
    }
}
