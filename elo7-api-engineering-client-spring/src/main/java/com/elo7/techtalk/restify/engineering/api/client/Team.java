package com.elo7.techtalk.restify.engineering.api.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {

	@JsonProperty
	private final String name;

	@JsonProperty
	private final Collection<Member> members;

	@JsonCreator
	public Team(@JsonProperty("name") String name, @JsonProperty("members") Collection<Member> members) {
		this.name = name;
		this.members = new ArrayList<>(members);
	}

	public String getName() {
		return name;
	}

	public Collection<Member> getMembers() {
		return members;
	}

	public void addMember(Member member) {
		members.add(member);
	}

	public Optional<Member> findMember(String memberName) {
		return members.stream().filter(m -> m.getName().equalsIgnoreCase(memberName)).findFirst();
	}
}
