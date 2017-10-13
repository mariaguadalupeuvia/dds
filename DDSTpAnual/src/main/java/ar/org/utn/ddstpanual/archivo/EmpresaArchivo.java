package ar.org.utn.ddstpanual.archivo;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.CuentaValor;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public interface EmpresaArchivo {

  public boolean exists(CuentaValor empresa) throws ArchivoException;

  public List<Empresa> obtenerEmpresas() throws ArchivoException;

  public List<Periodo> obtenerPeriodos(String nombreEmpresa) throws ArchivoException;

  public Empresa obtenerEmpresa(String nombre) throws ArchivoException;

}
