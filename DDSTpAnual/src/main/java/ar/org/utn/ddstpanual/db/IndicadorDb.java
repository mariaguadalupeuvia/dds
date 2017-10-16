package ar.org.utn.ddstpanual.db;

import java.util.List;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Indicador;

public interface IndicadorDb {

  public void guardarIndicador(Indicador indicador) throws DbException;

  public void eliminarIndicador(Indicador indicador) throws DbException;

  public List<Indicador> obtenerIndicadores() throws DbException;

  public boolean exists(Indicador indicador) throws DbException;

  public String obtenerFormula(String nombre) throws DbException;
  
  public String obtenerNombre(String formula) throws DbException;

}
