package ar.org.utn.ddstpanual.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.db.impl.RepositorioEmpresas;
import ar.org.utn.ddstpanual.db.impl.RepositorioMetodologias;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController 
{
	public ModelAndView listar(Request req, Response res){
		List<Metodologia> metodologias = RepositorioMetodologias.instancia.obtenerMetodologias();
		
		Map<String, List<Metodologia>> model = new HashMap<>();
		model.put("metodologias", metodologias);
		return new ModelAndView(model, "metodologias/listado.hbs");
	}
	
	public ModelAndView mostrar(Request req, Response res){
		String id = req.params("id");
		Metodologia metodologia = RepositorioMetodologias.instancia.buscar(Integer.parseInt(id));
		
		Map<String, Metodologia> model = new HashMap<>();
		model.put("metodologia", metodologia);
		return new ModelAndView(model, "metodologias/detalle.hbs");
	}
	
	public ModelAndView ejecutar(Request req, Response res){
		String id = req.params("id");
		String periodo = req.params("periodo");
		Metodologia metodologia = RepositorioMetodologias.instancia.buscar(Integer.parseInt(id));
		List<Empresa> empresas = metodologia.ejecutarMetodologia(RepositorioEmpresas.instancia.obtenerEmpresas(), new Periodo(periodo));
		
		Map<String, List<Empresa>> model = new HashMap<>();
		model.put("empresas", empresas);
		return new ModelAndView(model, "metodologias/ejecutado.hbs");
	}
}
