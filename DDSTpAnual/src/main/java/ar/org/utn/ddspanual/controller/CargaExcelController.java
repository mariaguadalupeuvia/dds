package ar.org.utn.ddspanual.controller;

import org.uqbar.commons.utils.Observable;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

@Observable
public class CargaExcelController {

  String rutaArchivo;
  String error;
  EmpresaService empresaService;

  public void guardarArchivo() {
    error = "";
    try {
      getEmpresaService().subirExcel(rutaArchivo);
    } catch (ServiceException e) {
      error = "Se produjo un error al intentar guardar el archivo. Intentelo nuevamente mas tarde.";
    }
  }

  public EmpresaService getEmpresaService() {
    if (empresaService != null) {
      return empresaService;
    }
    empresaService = new EmpresaServiceImpl();
    return empresaService;
  }

  public String getRutaArchivo() {
    return rutaArchivo;
  }

  public void setRutaArchivo(String rutaArchivo) {
    this.rutaArchivo = rutaArchivo;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}
