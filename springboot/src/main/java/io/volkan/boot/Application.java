package io.volkan.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Spring Boot makes creation and configuration of the apps easier.
//
// Core concepts:
//      - reduce the work required for spring developers to use the spring platform
//      - convention over configuration
//      - reasonable defaults that are easily customized
//      - dependency management without names and versions
//      - no code generation or XML.
//      - favors executable jars with embedded containers
//      - build tool integration (maven or gradle)
//      - spring tool suite
//      - spring boot cli
//      - spring initialize
//
// It gives developers choices; there is generally more than one way to do things right.

@RestController
@SpringBootApplication
//@EnableAutoConfiguration // Guess at our configuration.
//                         // Make intelligent guesses about the types of
//                         // configurations and types of beans in our project.
//// @Import(MyConfiguration.class) // Autowires our bean into our application class.
//@ComponentScan() // Start scanning from this classes package (io.volkan.boot), and recursively down below.
//                 // This is the default behavior if you don’t provide any path to it.
public class Application {

    @Autowired
    String message;

    @Value("${name}") // Injects from application.properties
    String name;

    @RequestMapping("/")
    public String home()  {
        return "Hello World " + name + " " + message;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
