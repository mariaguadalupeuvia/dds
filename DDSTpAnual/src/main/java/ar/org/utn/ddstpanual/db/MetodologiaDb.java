package ar.org.utn.ddstpanual.db;

import java.util.List;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;

public interface MetodologiaDb {

  public void guardarMetodologia(Metodologia metodologia) throws DbException;

  public Metodologia obtenerMetodologia(String nombre) throws DbException;

  public List<Metodologia> obtenerMetodologias() throws DbException;

}
