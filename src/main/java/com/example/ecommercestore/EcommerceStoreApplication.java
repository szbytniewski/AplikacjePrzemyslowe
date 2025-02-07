package com.example.ecommercestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.ecommercestore.entity")
@EnableJpaRepositories(basePackages = "com.example.ecommercestore.repository")
public class EcommerceStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceStoreApplication.class, args);
	}

}
