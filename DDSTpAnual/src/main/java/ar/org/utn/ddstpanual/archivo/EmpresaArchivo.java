package ar.org.utn.ddstpanual.archivo;

import java.util.List;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Periodo;

public interface EmpresaArchivo {

  public boolean exists(EmpresaExcel empresa) throws DbException;

  public List<Empresa> obtenerEmpresas() throws DbException;

  public List<Periodo> obtenerPeriodos(String nombreEmpresa) throws DbException;

  public Empresa obtenerEmpresa(String nombre) throws DbException;

}
