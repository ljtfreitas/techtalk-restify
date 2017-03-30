package com.elo7.techtalk.restify.engineering.api.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Team {

	private final String name;

	private final Collection<Member> members;

	public Team(String name, Collection<Member> members) {
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
