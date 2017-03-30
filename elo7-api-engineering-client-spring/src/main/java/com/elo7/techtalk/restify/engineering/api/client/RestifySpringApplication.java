package com.elo7.techtalk.restify.engineering.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestifySpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestifySpringApplication.class);
	}

	@Autowired
	private Elo7EngineeringService elo7EngineeringService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(elo7EngineeringService.allTeams());
	}
}
