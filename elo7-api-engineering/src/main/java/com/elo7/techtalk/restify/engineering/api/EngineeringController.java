package com.elo7.techtalk.restify.engineering.api;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elo7/engineering")
public class EngineeringController {

	private Collection<Team> teams = new ArrayList<>();

	@PostConstruct
	public void onCreate() {
		teams.add(new Team("Stark", Arrays.asList(new Member("Thiago Pilon"), new Member("Abner"), new Member("Isabela"))));
		teams.add(new Team("Martell", Arrays.asList(new Member("William"), new Member("Erika"), new Member("Thiago Vasconcelos"))));
	}

	@RequestMapping("/")
	public Collection<Team> allTeams() {
		return teams;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createTeam(@RequestBody Team team) {
		teams.add(team);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<Team> getTeamByName(@PathVariable("name") String name) {
		Optional<Team> team = teams.stream().filter(t -> t.getName().equalsIgnoreCase(name)).findFirst();

		return team.map(t -> ResponseEntity.ok(t))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@RequestMapping(value = "/{name}/members", method = RequestMethod.GET)
	public ResponseEntity<Collection<Member>> getMembersByTeam(@PathVariable("name") String name) {
		Optional<Team> team = teams.stream().filter(t -> t.getName().equalsIgnoreCase(name)).findFirst();

		return team.map(t -> ResponseEntity.ok(t.getMembers()))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@RequestMapping(value = "/{teamName}/{memberName}", method = RequestMethod.GET)
	public ResponseEntity<Member> getMemberByNameFromTeam(@PathVariable("teamName") String teamName, @PathVariable("memberName") String memberName) {
		Optional<Team> team = teams.stream().filter(t -> t.getName().equalsIgnoreCase(teamName)).findFirst();

		return team.flatMap(t -> t.findMember(memberName))
			.map(m -> ResponseEntity.ok(m))
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@RequestMapping(value = "/{teamName}", method = RequestMethod.POST)
	public ResponseEntity<Member> createMember(@PathVariable("teamName") String teamName, @RequestBody Member member) {
		Optional<Team> team = teams.stream().filter(t -> t.getName().equalsIgnoreCase(teamName)).findFirst();

		if (team.isPresent()) {
			team.get().addMember(member);

			return ResponseEntity.created(fromMethodCall(on(this.getClass()).getMemberByNameFromTeam(teamName, member.getName().toLowerCase())).build().toUri())
					.body(member);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Collection<Member> searchMembersByName(@RequestParam("member_name") String memberName) {
		return teams.stream()
				.flatMap(t -> t.getMembers().stream())
					.filter(m -> m.getName().startsWith(memberName))
						.collect(Collectors.toList());
	}

	@RequestMapping("/server")
	public Map<String, String> server(HttpServletRequest httpServletRequest) {
		Map<String, String> json = new LinkedHashMap<>();

		json.put("host", httpServletRequest.getServerName());
		json.put("port", Integer.toString(httpServletRequest.getServerPort()));

		return json;
	}
}
