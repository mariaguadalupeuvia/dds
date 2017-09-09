package ar.org.utn.ddstpanual.db;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public interface EmpresaDb {

  public void guardarEmpresa(Empresa empresa) throws ArchivoException;

  public boolean exists(Empresa empresa) throws ArchivoException;

  public List<Empresa> obtenerEmpresas() throws ArchivoException;

  public List<Periodo> obtenerPeriodos(String nombreEmpresa) throws ArchivoException;

  public Empresa obtenerEmpresa(String nombre) throws ArchivoException;

}
