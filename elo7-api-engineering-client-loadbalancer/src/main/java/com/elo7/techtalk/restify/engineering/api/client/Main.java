package com.elo7.techtalk.restify.engineering.api.client;

import com.github.ljtfreitas.restify.http.RestifyProxyBuilder;
import com.github.ljtfreitas.restify.http.netflix.client.request.RibbonHttpClientRequestFactory;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;

public class Main {

	public static void main(String[] args) throws Exception {
		BaseLoadBalancer loadBalancer = new BaseLoadBalancer();
		loadBalancer.addServer(new Server("localhost", 8080));
		loadBalancer.addServer(new Server("localhost", 8081));
		loadBalancer.addServer(new Server("localhost", 8082));

		RibbonHttpClientRequestFactory ribbonHttpClientRequestFactory = new RibbonHttpClientRequestFactory(loadBalancer);

		Elo7EngineeringService elo7EngineeringService = new RestifyProxyBuilder()
				.client(ribbonHttpClientRequestFactory)
				.target(Elo7EngineeringService.class, "http://elo7-engineering-api")
					.build();

		System.out.println(elo7EngineeringService.about());
		System.out.println(elo7EngineeringService.about());
		System.out.println(elo7EngineeringService.about());
	}
}
