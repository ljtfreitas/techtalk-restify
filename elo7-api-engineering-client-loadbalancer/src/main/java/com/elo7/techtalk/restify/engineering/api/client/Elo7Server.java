package com.elo7.techtalk.restify.engineering.api.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Elo7Server {

	private final String host;
	private final int port;

	@JsonCreator
	public Elo7Server(@JsonProperty("host") String host, @JsonProperty("port") int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public String toString() {
		return "Elo7 API server -> " + host + ":" + port;
	}
}
