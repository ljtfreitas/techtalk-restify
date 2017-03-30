package com.elo7.techtalk.restify.engineering.api.client;

import com.github.ljtfreitas.restify.http.RestifyProxyBuilder;
import com.github.ljtfreitas.restify.http.netflix.client.request.RibbonHttpClientRequestFactory;
import com.github.ljtfreitas.restify.http.netflix.client.request.zookeeper.ZookeeperConfiguration;
import com.github.ljtfreitas.restify.http.netflix.client.request.zookeeper.ZookeeperServers;
import com.github.ljtfreitas.restify.http.netflix.client.request.zookeeper.ZookeeperServiceDiscovery;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;

public class Main {

	public static void main(String[] args) throws Exception {
		ZookeeperConfiguration zookeeperDiscoveryConfiguration = new ZookeeperConfiguration("localhost", 2181, "/services");

		ZookeeperServiceDiscovery zookeeperServiceDiscovery = new ZookeeperServiceDiscovery(zookeeperDiscoveryConfiguration);

		ZookeeperServers zookeeperServers = new ZookeeperServers("elo7-engineering-api", zookeeperServiceDiscovery);

		ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder()
				.withDynamicServerList(zookeeperServers)
				.buildDynamicServerListLoadBalancer();

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
