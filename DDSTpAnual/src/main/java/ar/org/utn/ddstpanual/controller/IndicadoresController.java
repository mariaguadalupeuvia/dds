package ar.org.utn.ddstpanual.controller;

import org.uqbar.commons.utils.Observable;

import Repositorio.RepositorioEmpresas;
import Repositorio.RepositorioIndicadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
	
  Indicador indicador;
  List<Empresa> empresas;
  Empresa empresaCheckbox;
  List<Periodo> periodos;
  Periodo periodoCheckbox;
 

	public ModelAndView ejecutar(Request req, Response res){
		String periodoSeleccionado = req.queryParams("periodoSeleccionado");
		Empresa empresa = RepositorioEmpresas.instancia.obtenerEmpresa(req.queryParams("empresaSeleccionada"));
		Map<String, List<FormulaIndicador>> model = new HashMap<>();
	
	    List<FormulaIndicador> indicadoresEvaluados = new ArrayList<>();
		RepositorioIndicadores.instancia.obtenerIndicadores().stream().map(indicador->indicadoresEvaluados.add( new FormulaIndicador(indicador.getNombre(), periodoSeleccionado, indicador.ejecutarIndicador(periodoSeleccionado, empresa)))).collect(Collectors.toList());
		model.put("indicadoresEvaluados", indicadoresEvaluados);
		
		return new ModelAndView(model, "indicadores/indicadorEvaluado.hbs");
	}
	
	public ModelAndView listar(Request req, Response res){
		List<Empresa> empresas = RepositorioEmpresas.instancia.obtenerEmpresas();
		
		Map<String, List<Empresa>> model = new HashMap<>();
		model.put("empresas", empresas);
		return new ModelAndView(model, "indicadores/listadoIndicadores.hbs");
	}
  
  public List<Indicador> obtenerIndicadores() 
  {
    return RepositorioIndicadores.instancia.obtenerIndicadores();
  }

  public Double ejecutarIndicador() 
  {
     return indicador.ejecutarIndicador(periodoCheckbox.getFecha(), empresaCheckbox);
  }



}
