package ar.org.utn.ddstpanual.controller;

import org.uqbar.commons.utils.Observable;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.MetodologiaService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;
import ar.org.utn.ddstpanual.service.impl.MetodologiaServiceImpl;

@Observable
public class MetodologiasController {

  MetodologiaService metodologiaService;
  EmpresaService empresaService;
  List<Empresa> empresas;
  List<Metodologia> metodologias;
  Metodologia metodologiaCheckbox;

  List<Empresa> empresasResultado;

  String error;

  public void inicializarVariables() {
    error = "";
    obtenerEmpresas();
    obtenerMetodologias();
  }

  public void obtenerEmpresas() {
    error = "";
    try {
      empresas = getEmpresaService().obtenerEmpresas();
    } catch (final ServiceException e) {
      error = "Se produjo un error al obtener las empresas.";
    }
  }

  public void obtenerMetodologias() {
    error = "";
    try {
      metodologias = getMetodologiaService().obtenerMetodologias();
    } catch (final ServiceException e) {
      error = "Se produjo un error al obtener las metodologias.";
    }
  }

  public void ejecutarMetodologia() {
    try {
      empresasResultado = getMetodologiaService().ejecutarMetodologia(empresas, metodologiaCheckbox);
    } catch (final ServiceException e) {
      error = "Se produjo un error al obtener las empresas.";
    }
  }

  public EmpresaService getEmpresaService() {
    if (empresaService != null) {
      return empresaService;
    }
    empresaService = new EmpresaServiceImpl();
    return empresaService;
  }

  public MetodologiaService getMetodologiaService() {
    if (metodologiaService != null) {
      return metodologiaService;
    }
    metodologiaService = new MetodologiaServiceImpl();
    return metodologiaService;
  }

  public List<Empresa> getEmpresas() {
    return empresas;
  }

  public void setEmpresas(List<Empresa> empresas) {
    this.empresas = empresas;
  }

  public List<Empresa> getEmpresasResultado() {
    return empresasResultado;
  }

  public void setEmpresasResultado(List<Empresa> empresasResultado) {
    this.empresasResultado = empresasResultado;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public void setEmpresaService(EmpresaService empresaService) {
    this.empresaService = empresaService;
  }

  public List<Metodologia> getMetodologias() {
    return metodologias;
  }

  public void setMetodologias(List<Metodologia> metodologias) {
    this.metodologias = metodologias;
  }

  public Metodologia getMetodologiaCheckbox() {
    return metodologiaCheckbox;
  }

  public void setMetodologiaCheckbox(Metodologia metodologiaCheckbox) {
    this.metodologiaCheckbox = metodologiaCheckbox;
  }



}
