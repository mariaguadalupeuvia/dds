package ar.org.utn.ddspanual.controller;

import org.uqbar.commons.utils.Observable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

@Observable
public class CargaExcelController {

  String rutaArchivo;
  String error;
  EmpresaService empresaService;

  public void guardarArchivo() {
    try {
      File archivo = new File(rutaArchivo);
      FileInputStream fileStream = new FileInputStream(archivo);
      getEmpresaService().subirExcel(fileStream);
    } catch (FileNotFoundException | ServiceException e) {
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
