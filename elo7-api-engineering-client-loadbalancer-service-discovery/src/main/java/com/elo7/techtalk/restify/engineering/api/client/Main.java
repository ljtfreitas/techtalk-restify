package com.elo7.techtalk.restify.engineering.api.client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.details.InstanceSerializer;

import com.github.ljtfreitas.restify.http.RestifyProxyBuilder;
import com.github.ljtfreitas.restify.http.netflix.client.request.RibbonHttpClientRequestFactory;
import com.github.ljtfreitas.restify.http.netflix.client.request.zookeeper.ZookeeperDiscoveryConfiguration;
import com.github.ljtfreitas.restify.http.netflix.client.request.zookeeper.ZookeeperInstance;
import com.github.ljtfreitas.restify.http.netflix.client.request.zookeeper.ZookeeperInstanceSerializer;
import com.github.ljtfreitas.restify.http.netflix.client.request.zookeeper.ZookeeperServers;
import com.github.ljtfreitas.restify.http.netflix.client.request.zookeeper.ZookeeperServiceDiscovery;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;

public class Main {

	public static void main(String[] args) throws Exception {
		ZookeeperDiscoveryConfiguration zookeeperDiscoveryConfiguration = new ZookeeperDiscoveryConfiguration();

		zookeeperDiscoveryConfiguration.root("/services");

		CuratorFramework curator = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.retryPolicy(new RetryNTimes(0, 0))
				.build();

		curator.start();

		InstanceSerializer<ZookeeperInstance> serializer = new ZookeeperInstanceSerializer();

		ZookeeperServiceDiscovery zookeeperServiceDiscovery = new ZookeeperServiceDiscovery(zookeeperDiscoveryConfiguration, curator, serializer);

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
