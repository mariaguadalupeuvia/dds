package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Repositorio.RepositorioEmpresas;
import Repositorio.RepositorioMetodologias;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;
import db.FixtureDB;
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
	
	public ModelAndView ejecutar(Request req, Response res){
		String metodologiaSeleccionada = req.queryParams("metodologiaSeleccionada");
		String periodoSeleccionado = req.queryParams("periodoSeleccionado");
		Metodologia metodologia = RepositorioMetodologias.instancia.obtenerMetodologia(metodologiaSeleccionada);
		List<Empresa> empresas = metodologia.ejecutarMetodologia(RepositorioEmpresas.instancia.obtenerEmpresas(), new Periodo(periodoSeleccionado));
		Map<String, List<Empresa>> model = new HashMap<>();
		model.put("empresas", empresas);
		return new ModelAndView(model, "metodologias/ejecutado.hbs");
	}
	
	public  ModelAndView crear(Request req, Response res){
		FixtureDB fixture = new FixtureDB();
		Map<String, Object> model = new HashMap<>();
		model.put("filtros", fixture.getFiltros());
		model.put("indicadores", fixture.getIndicadores());
		return new ModelAndView(model, "metodologias/nuevo.hbs");
	}
	public void guardar(Request req, Response res){
		 List<Condicion> condiciones = new ArrayList<Condicion>();
		
	//	Metodologia metodologia = new Metodologia();
		//RepositorioMetodologias.instancia.guardarMetodologia(metodologia);
		
		res.redirect("/metodologias");
	}
}
