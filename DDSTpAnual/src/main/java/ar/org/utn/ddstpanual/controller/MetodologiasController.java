package ar.org.utn.ddstpanual.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.db.impl.RepositorioEmpresas;
import ar.org.utn.ddstpanual.db.impl.RepositorioMetodologias;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;
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
	
//	public ModelAndView mostrar(Request req, Response res){
//		String id = req.params("id");
//		Metodologia metodologia = RepositorioMetodologias.instancia.buscar(Integer.parseInt(id));
//		
//		Map<String, Metodologia> model = new HashMap<>();
//		model.put("metodologia", metodologia);
//		return new ModelAndView(model, "metodologias/detalle.hbs");
//	}
	
	public ModelAndView ejecutar(Request req, Response res){
		String metodologiaSeleccionada = req.queryParams("metodologiaSeleccionada");
		String periodoSeleccionado = req.queryParams("periodoSeleccionado");
		Metodologia metodologia = RepositorioMetodologias.instancia.obtenerMetodologia(metodologiaSeleccionada);
		List<Empresa> empresas = metodologia.ejecutarMetodologia(RepositorioEmpresas.instancia.obtenerEmpresas(), new Periodo(periodoSeleccionado));
		Map<String, List<Empresa>> model = new HashMap<>();
		model.put("empresas", empresas);
		return new ModelAndView(model, "metodologias/ejecutado.hbs");
	}
	
	public Void crear(Request req, Response res){
		System.out.println(req.queryParams("condiciones"));
		Map< List<Condicion>, String> model = new HashMap<>();
		 List<Condicion> condiciones = null;
		model.put(condiciones, req.queryParams("condiciones"));
//		 List<Condicion> condiciones = (List<Condicion>)req.queryParams("condiciones");
//		 List<Orden> ordenes = (List<Orden>)req.queryParams("ordenes");
//		Metodologia metodologia = new Metodologia(req.queryParams("nombre"),condiciones, ordenes);
//		RepositorioMetodologias.instancia.guardarMetodologia(metodologia);
		res.redirect("/metodologias");
		return null;
	}
}
