package com.example.springboot.project;

import com.example.springboot.project.entities.Product;
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
		Product product1 = new Product();
		product1.setProductName("Samsung Washcare");
		product1.setProductPrice(73627);
		product1.setProductDescription("It's an automatic washing machine");
		productRepository.save(product1);

		Product product2 = new Product();
		product2.setProductName("OnePlus Earbuds");
		product2.setProductPrice(2450);
		product2.setProductDescription("It's a Bluetooth earbud");
		productRepository.save(product2);
	}
}
