package io.volkan.springmvc;

import io.volkan.springmvc.entities.Cat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SpringMvcApplication {

    @RequestMapping("/viewresolver")
    public String viewResolver() {
        return "viewresolver"; // takes the logical name of the viewresolver, and maps that
                               // into one of the actual ThymeLeaf templates.
    }

    @ResponseBody // The returned object will be written directly to the response body.
    @RequestMapping("/cat") // routes request to the specific controller methods.
    public Cat cat() {
        return new Cat("Athena", "Bengal", 1);
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcApplication.class, args);
	}
}
