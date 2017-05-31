package ar.org.utn.ddspanual.controller;

import org.uqbar.commons.utils.Observable;

import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;

@Observable
public class IndicadoresController {

  IndicadorService indicadorService;
  EmpresaService empresaService;
  List<String> indicadores;
  Map<String, Empresa> empresas;
  List<String> empresasCheckbox;
  List<String> cuentasCheckbox;
  List<String> periodosCheckbox;
  List<EmpresaExcel> tabla;
  String error;

  public Map<String, Empresa> obtenerEmpresas() {
    try {
      empresas = getEmpresaService().obtenerEmpresas();
    } catch (ServiceException e) {
      error = "Se produjo un error al obtener las empresas.";
    }
    return empresas;
  }

  public List<String> obtenerIndicadores() {
    try {
      indicadores = getIndicadorService().obtenerNombresIndicadores();
    } catch (ServiceException e) {
      error = "Se produjo un error al obtener los indicadores.";
    }
    return indicadores;
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

  public List<String> getIndicadores() {
    return indicadores;
  }

  public void setIndicadores(List<String> indicadores) {
    this.indicadores = indicadores;
  }

  public Map<String, Empresa> getEmpresas() {
    return empresas;
  }

  public void setEmpresas(Map<String, Empresa> empresas) {
    this.empresas = empresas;
  }

  public List<String> getEmpresasCheckbox() {
    return empresasCheckbox;
  }

  public void setEmpresasCheckbox(List<String> empresasCheckbox) {
    this.empresasCheckbox = empresasCheckbox;
  }

  public List<String> getCuentasCheckbox() {
    return cuentasCheckbox;
  }

  public void setCuentasCheckbox(List<String> cuentasCheckbox) {
    this.cuentasCheckbox = cuentasCheckbox;
  }

  public List<String> getPeriodosCheckbox() {
    return periodosCheckbox;
  }

  public void setPeriodosCheckbox(List<String> periodosCheckbox) {
    this.periodosCheckbox = periodosCheckbox;
  }

  public List<EmpresaExcel> getTabla() {
    return tabla;
  }

  public void setTabla(List<EmpresaExcel> tabla) {
    this.tabla = tabla;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}
