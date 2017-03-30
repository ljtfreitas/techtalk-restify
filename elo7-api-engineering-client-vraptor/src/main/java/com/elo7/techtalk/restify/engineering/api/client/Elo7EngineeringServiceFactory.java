package com.elo7.techtalk.restify.engineering.api.client;

import javax.enterprise.inject.Produces;

import com.github.ljtfreitas.restify.http.RestifyProxyBuilder;

public class Elo7EngineeringServiceFactory {

	@Produces
	public Elo7EngineeringService elo7EngineeringService() {
		return new RestifyProxyBuilder()
			.target(Elo7EngineeringService.class, "http://localhost:8080/").build();
		
	}
}
