package ar.org.utn.ddstpanual.db;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;

public interface MetodologiaDb {

  public void guardarMetodologia(Metodologia metodologia) throws ArchivoException;

  public Metodologia obtenerMetodologia(String nombre) throws ArchivoException;

  public List<Metodologia> obtenerMetodologias() throws ArchivoException;

}
