package br.com.ecommerceLux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EcommerceLuxApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceLuxApplication.class, args);
	}

}
