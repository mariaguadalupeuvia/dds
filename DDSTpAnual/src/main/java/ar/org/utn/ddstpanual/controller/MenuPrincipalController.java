package ar.org.utn.ddstpanual.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Repositorio.RepositorioMetodologias;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MenuPrincipalController {
	public ModelAndView home(Request req, Response res){
		
		List<Metodologia> metodologias = RepositorioMetodologias.instancia.obtenerMetodologias();
		Map<String, List<Metodologia>> model = new HashMap<>();
		model.put("metodologias", metodologias);
		return new ModelAndView(model, "home.hbs");
	}
	
	public ModelAndView css(Request req, Response res){
		
		return new ModelAndView(null, "estilo.css");
	}

}
