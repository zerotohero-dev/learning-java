package io.volkan.springbootjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringBootJdbcApplication {

	private final JdbcTemplate template;

    @Autowired
    public SpringBootJdbcApplication(JdbcTemplate template) {
        this.template = template;
    }

    @RequestMapping("/stocks")
    public List<Map<String, Object>> stocks() {
        return template.queryForList("SELECT * FROM stock");
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);
	}
}
