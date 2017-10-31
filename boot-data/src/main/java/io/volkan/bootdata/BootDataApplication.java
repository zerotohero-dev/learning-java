package io.volkan.bootdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootApplication
@RestController
public class BootDataApplication {

	// private final EntityManager em;
	private final StockRepository repo;

	@Autowired
	public BootDataApplication(StockRepository repo) {
		this.repo = repo;
	}

	@RequestMapping("/stocks/{symbol}")
	public Stock stocks(@PathVariable("symbol") String symbol) {
		// return em.createQuery("SELECT s FROM Stock s").getResultList();
		return repo.findBySymbol(symbol);
	}

	public static void main(String[] args) {
		SpringApplication.run(BootDataApplication.class, args);
	}
}
