package com.elo7.techtalk.restify.engineering.api.client;

import java.util.Collection;

import com.github.ljtfreitas.restify.http.client.request.async.EndpointCallCallback;
import com.github.ljtfreitas.restify.http.contract.BodyParameter;
import com.github.ljtfreitas.restify.http.contract.CallbackParameter;
import com.github.ljtfreitas.restify.http.contract.Get;
import com.github.ljtfreitas.restify.http.contract.JsonContent;
import com.github.ljtfreitas.restify.http.contract.Path;
import com.github.ljtfreitas.restify.http.contract.PathParameter;
import com.github.ljtfreitas.restify.http.contract.Post;
import com.github.ljtfreitas.restify.http.contract.QueryParameter;

@Path("/elo7/engineering")
public interface Elo7EngineeringService {

	@Path("/")
	@Get
	public Collection<Team> allTeams();

	@Path("/")
	@Post
	@JsonContent
	public void createTeam(@BodyParameter Team team);

	@Path("/{teamName}")
	@Get
	public Team getTeamByName(@PathParameter("teamName") String teamName);

	@Path("/{teamName}")
	@Get
	public void getTeamByName(@PathParameter("teamName") String teamName,
			@CallbackParameter EndpointCallCallback<Team> team);

	@Path("/{teamName}/members")
	@Get
	public Collection<Member> getMembersByTeam(@PathParameter("teamName") String teamName);

	@Path("/{teamName}/{memberName}")
	@Get
	public Member getMemberByNameFromTeam(@PathParameter("teamName") String teamName,
			@PathParameter("memberName") String memberName);

	@Path(value = "/{teamName}")
	@Post
	@JsonContent
	public Member createMember(@PathParameter("teamName") String teamName, @BodyParameter Member member);

	@Path(value = "/search")
	@Get
	public Collection<Member> search(@QueryParameter("member_name") String memberName);
}
