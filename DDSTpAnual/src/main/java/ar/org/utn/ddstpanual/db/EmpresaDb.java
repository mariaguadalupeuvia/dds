package ar.org.utn.ddstpanual.db;

import java.util.List;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public interface EmpresaDb {

  public void guardarEmpresa(Empresa empresa) throws DbException;

  public boolean exists(Empresa empresa) throws DbException;

  public List<Empresa> obtenerEmpresas() throws DbException;

  public List<Periodo> obtenerPeriodos() throws DbException;

  public Empresa obtenerEmpresa(String nombreEmpresa) throws DbException;
  
  public Cuenta obtenerCuenta(Integer idEmpresa, String nombreCuenta) throws DbException;
  
  public Periodo obtenerPeriodo(Integer idCuenta, String fecha) throws DbException;

}
