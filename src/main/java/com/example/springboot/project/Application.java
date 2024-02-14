package com.example.springboot.project;

import com.example.springboot.project.model.Product;
import com.example.springboot.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private ProductRepository productRepository;
	@Override
	public void run(String... args) throws Exception {
		Product p = new Product();
		p.setProductName("samsung washcare");
		p.setProductPrice(73627);
		p.setProductDescription("its a automatic washing machine");
		productRepository.save(p);

		Product p1 = new Product();
		p1.setProductName("onePluse earbud");
		p1.setProductPrice(2450);
		p1.setProductDescription("its a bluethooth earbud");
		productRepository.save(p1);

	}
}
