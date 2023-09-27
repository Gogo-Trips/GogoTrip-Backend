package com.example.gogotrips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GogotripsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GogotripsApplication.class, args);
	}

}
