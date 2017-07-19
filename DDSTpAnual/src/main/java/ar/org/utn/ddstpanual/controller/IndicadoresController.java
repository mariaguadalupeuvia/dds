package ar.org.utn.ddstpanual.controller;

import org.uqbar.commons.utils.Observable;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;

@Observable
public class IndicadoresController {

  IndicadorService indicadorService;
  EmpresaService empresaService;
  List<Indicador> indicadores;
  Indicador indicador;
  List<Empresa> empresas;
  Empresa empresaCheckbox;
  List<Periodo> periodos;
  // Cuenta cuentaCheckbox;
  Periodo periodoCheckbox;
  // List<EmpresaExcel> tabla;
  List<FormulaIndicador> formulaIndicador;
  String error;

  public List<Empresa> obtenerEmpresas() {
    error = "";
    try {
      empresas = getEmpresaService().obtenerEmpresas();
    } catch (final ServiceException e) {
      error = "Se produjo un error al obtener las empresas.";
    }
    return empresas;
  }

  public List<Periodo> obtenerPeriodos() {
    error = "";
    try {
      periodos = getEmpresaService().obtenerPeriodos();
    } catch (final ServiceException e) {
      error = "Se produjo un error al obtener las empresas.";
    } catch (final NullPointerException e) {
      error = "No existen períodos para estas empresas";
      System.out.println(error);
    }
    return periodos;
  }

  public List<Indicador> obtenerIndicadores() {
    error = "";
    try {
      indicadores = getIndicadorService().obtenerIndicadores();
    } catch (final ServiceException e) {
      error = "Se produjo un error al obtener los indicadores.";
    }
    return indicadores;
  }

  public List<FormulaIndicador> ejecutarIndicador() {
    error = "";
    try {
      formulaIndicador = getIndicadorService().ejecutarIndicador(indicador.getNombre(), periodoCheckbox, empresaCheckbox);
    } catch (final ServiceException e) {
      error = e.getMessage();
    } 
    return formulaIndicador;
  }

  public IndicadorService getIndicadorService() {
    if (indicadorService != null) {
      return indicadorService;
    }
    indicadorService = new IndicadorServiceImpl();
    return indicadorService;
  }

  public EmpresaService getEmpresaService() {
    if (empresaService != null) {
      return empresaService;
    }
    empresaService = new EmpresaServiceImpl();
    return empresaService;
  }

  public Indicador getIndicador() {
    return indicador;
  }

  public void setIndicador(final Indicador indicador) {
    this.indicador = indicador;
  }

  public List<Indicador> getIndicadores() {
    return indicadores;
  }

  public void setIndicadores(final List<Indicador> indicadores) {
    this.indicadores = indicadores;
  }

  public List<Empresa> getEmpresas() {
    return empresas;
  }

  public void setEmpresas(final List<Empresa> empresas) {
    this.empresas = empresas;
  }

  public Empresa getEmpresaCheckbox() {
    return empresaCheckbox;
  }

  public void setEmpresaCheckbox(final Empresa empresaCheckbox) {
    this.empresaCheckbox = empresaCheckbox;
  }

  /*
   * public Cuenta getCuentaCheckbox() { return cuentaCheckbox; }
   * 
   * public void setCuentaCheckbox(Cuenta cuentaCheckbox) { this.cuentaCheckbox = cuentaCheckbox; }
   */
  public Periodo getPeriodoCheckbox() {
    return periodoCheckbox;
  }

  public void setPeriodoCheckbox(final Periodo periodoCheckbox) {
    this.periodoCheckbox = periodoCheckbox;
  }

  /*
   * public List<EmpresaExcel> getTabla() { return tabla; }
   * 
   * public void setTabla(List<EmpresaExcel> tabla) { this.tabla = tabla; }
   */
  public String getError() {
    return error;
  }

  public void setError(final String error) {
    this.error = error;
  }

  public List<FormulaIndicador> getFormulaIndicador() {
    return formulaIndicador;
  }

  public void setFormulaIndicador(final List<FormulaIndicador> formulaIndicador) {
    this.formulaIndicador = formulaIndicador;
  }

  public List<Periodo> getPeriodos() {
    return periodos;

  }

  public void setPeriodos(final List<Periodo> periodos) {
    this.periodos = periodos;
  }
}
