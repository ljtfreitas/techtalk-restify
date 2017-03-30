package com.elo7.techtalk.restify.engineering.api.client;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

@Controller
public class VraptorController {

	@Inject
	private Result result;

	@Inject
	private Elo7EngineeringService elo7EngineeringService;
	
	@Get("/vraptor/sample")
	public void allTeams() {
		result.use(json()).from(elo7EngineeringService.allTeams()).serialize();
	}
}
