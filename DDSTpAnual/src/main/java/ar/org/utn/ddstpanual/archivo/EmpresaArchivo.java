package ar.org.utn.ddstpanual.archivo;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;

public interface EmpresaArchivo {

  public void guardarEmpresa(EmpresaExcel empresa) throws ArchivoException;

  public boolean exists(EmpresaExcel empresa) throws ArchivoException;

  public List<Empresa> obtenerEmpresas() throws ArchivoException;

}
