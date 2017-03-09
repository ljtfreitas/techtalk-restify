package com.elo7.techtalk.restify.engineering.api.client;

import java.util.Collection;

import com.github.ljtfreitas.restify.http.contract.Get;
import com.github.ljtfreitas.restify.http.contract.Path;

@Path("/elo7/engineering")
public interface Elo7EngineeringService {

	@Path("/") @Get
	public Collection<Team> allTeams();

	@Path("/server") @Get
	public Elo7Server about();
}
