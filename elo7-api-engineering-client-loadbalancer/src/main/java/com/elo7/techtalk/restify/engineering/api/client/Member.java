package com.elo7.techtalk.restify.engineering.api.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

	@JsonProperty
	private String name;

	@JsonCreator
	public Member(@JsonProperty("name") String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
