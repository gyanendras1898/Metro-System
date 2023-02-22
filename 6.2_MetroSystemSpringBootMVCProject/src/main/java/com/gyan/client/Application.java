package com.gyan.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.gyan")
@EnableJpaRepositories(basePackages = "com.gyan.model.persistence")
@EntityScan(basePackages = "com.gyan.beans")
public class Application extends SpringBootServletInitializer {
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
//	public String PORT = System.getenv("PORT");
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
