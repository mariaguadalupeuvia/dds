package ar.org.utn.ddstpanual.controller;

import org.uqbar.commons.utils.Observable;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
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
  Periodo periodoCheckbox;
  List<Periodo> periodos;

  List<Empresa> empresasResultado;

  String error;

  public void inicializarVariables() {
    error = "";
    obtenerEmpresas();
    obtenerMetodologias();
    obtenerPeriodos();
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

  public void ejecutarMetodologia() {
    error = "";
    try {
      empresasResultado = getMetodologiaService().ejecutarMetodologia(empresas, metodologiaCheckbox, periodoCheckbox);
      if (empresasResultado.isEmpty())
        error = "No se encuentran empresas que cumplan estas condiciones \n para el año " + periodoCheckbox.getFecha();
    } catch (final NullPointerException n) {
      error = "Debe completar todos los campos";
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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

  public Periodo getPeriodoCheckbox() {
    return periodoCheckbox;
  }

  public void setPeriodoCheckbox(final Periodo periodoCheckbox) {
    this.periodoCheckbox = periodoCheckbox;
  }

  public List<Periodo> getPeriodos() {
    return periodos;

  }

  public void setPeriodos(final List<Periodo> periodos) {
    this.periodos = periodos;
  }


}
