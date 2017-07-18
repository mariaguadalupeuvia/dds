package ar.org.utn.ddstpanual.controller;

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
      final String ruta = rutaArchivo + "";
      getEmpresaService().subirExcel(ruta);
    } catch (final ServiceException e) {
      error = "Se produjo un error al intentar guardar el archivo. Intentelo nuevamente.";
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

  public void setRutaArchivo(final String rutaArchivo) {
    this.rutaArchivo = rutaArchivo;
  }

  public String getError() {
    return error;
  }

  public void setError(final String error) {
    this.error = error;
  }

}
