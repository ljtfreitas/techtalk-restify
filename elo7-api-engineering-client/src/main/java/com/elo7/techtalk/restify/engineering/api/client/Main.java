package com.elo7.techtalk.restify.engineering.api.client;

import com.github.ljtfreitas.restify.http.RestifyProxyBuilder;

public class Main {

	public static void main(String[] args) {
		Elo7EngineeringService elo7EngineeringService = new RestifyProxyBuilder()
				.target(Elo7EngineeringService.class, "http://localhost:8080")
					.build();

		elo7EngineeringService.search("Thiago").forEach(m -> System.out.println(m.getName()));
	}
}
