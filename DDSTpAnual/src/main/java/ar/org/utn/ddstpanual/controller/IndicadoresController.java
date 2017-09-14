package ar.org.utn.ddstpanual.controller;

import org.uqbar.commons.utils.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.db.impl.IndicadorDbImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IndicadoresController {

  IndicadorDb indicadorDb = new IndicadorDbImpl();
  EmpresaService empresaService;
  List<Indicador> indicadores;
  Indicador indicador;
  List<Empresa> empresas;
  Empresa empresaCheckbox;
  List<Periodo> periodos;
  Periodo periodoCheckbox;
  private List<FormulaIndicador> indicadoresEvaluados = new ArrayList<>();
  String error;

  public void limpiar()
  {
	  error = "";
	  empresaCheckbox = null;
	  periodoCheckbox = null;
	  indicadoresEvaluados.clear();
  }
  
  public List<Empresa> obtenerEmpresas() {
    try {
      empresas = getEmpresaService().obtenerEmpresas();
    } catch (final ServiceException e) {
      error = "Se produjo un error al obtener las empresas.";
    }
    return empresas;
  }

  public List<Periodo> obtenerPeriodos() {
    try {
      periodos = getEmpresaService().obtenerPeriodos();
    } catch (final ServiceException e) {
    	 error = "Se produjo un error al obtener las empresas.";
    } catch (final NullPointerException e) {
    	 error = "No existen períodos para estas empresas";
    }
    return periodos;
  }

  public List<Indicador> obtenerIndicadores() {
    try {
      indicadores = indicadorDb.obtenerIndicadores();
    } catch (ArchivoException e) {
    	 error = e.getMessage();
	}
    return indicadores;
  }

  public Double ejecutarIndicador() {
	  error = "";
    try {
      if (periodoCheckbox == null)
      {
        periodoCheckbox = new Periodo("");
      }
     return indicador.ejecutarIndicador(periodoCheckbox.getFecha(), empresaCheckbox);
    } catch (final NullPointerException n) {
    	 error = "Debe completar los campos \"Empresa\" y \"periodo\"";
    }
	return null;
  }

  public EmpresaService getEmpresaService() {
    if (empresaService != null) {
      return empresaService;
    }
    empresaService = new EmpresaServiceImpl();
    return empresaService;
  }

public void listarIndicadores(){
	error = "";
    if ((periodoCheckbox == null) || (empresaCheckbox == null))
    {
    	error = "Debe completar los campos \"Empresa\" y \"periodo\"";
    	return;
    }
	try {
		indicadores = indicadorDb.obtenerIndicadores();
	} catch (ArchivoException e) {
		 error = e.getMessage();
	}
	indicadoresEvaluados.clear();
	indicadores.stream().map(indicador->indicadoresEvaluados.add( new FormulaIndicador(indicador.getNombre(), periodoCheckbox.getFecha(), indicador.ejecutarIndicador(periodoCheckbox.getFecha(), empresaCheckbox)))).collect(Collectors.toList());
}

}
