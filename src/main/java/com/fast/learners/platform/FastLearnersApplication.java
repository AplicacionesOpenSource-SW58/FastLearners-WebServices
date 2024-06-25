package com.fast.learners.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class FastLearnersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastLearnersApplication.class, args);
	}

}
