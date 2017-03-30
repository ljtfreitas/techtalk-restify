package com.elo7.techtalk.restify.engineering.api.client;

import java.util.Collection;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.ljtfreitas.restify.spring.configure.Restifyable;

@Restifyable(endpoint = "http://localhost:8080/elo7/engineering")
public interface Elo7EngineeringService {

	@GetMapping("/")
	public Collection<Team> allTeams();

	@PostMapping(path = "/", consumes = "application/json")
	public void createTeam(@RequestBody Team team);

	@GetMapping("/{teamName}")
	public Team getTeamByName(@PathVariable("teamName") String teamName);

	@GetMapping("/{teamName}")
	public ListenableFuture<Team> getTeamByNameAsync(@PathVariable("teamName") String teamName);

	@GetMapping("/{teamName}/members")
	public Collection<Member> getMembersByTeam(@PathVariable("teamName") String teamName);

	@GetMapping("/{teamName}/{memberName}")
	public Member getMemberByNameFromTeam(@PathVariable("teamName") String teamName,
			@PathVariable("memberName") String memberName);

	@PostMapping(path = "/{teamName}", consumes = "application/json")
	public Member createMember(@PathVariable("teamName") String teamName, @RequestBody Member member);

	@GetMapping("/search")
	public Collection<Member> search(@RequestParam("member_name") String memberName);
}
