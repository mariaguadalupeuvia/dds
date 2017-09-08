package ar.org.utn.ddstpanual.db;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Indicador;

public interface IndicadorDb {

	public void guardarIndicador(Indicador indicador) throws ArchivoException;

	public void eliminarIndicador(Indicador indicador) throws ArchivoException;

	public List<Indicador> obtenerIndicadores() throws ArchivoException;

	public boolean exists(Indicador indicador) throws ArchivoException;

	public String obtenerFormula(String nombre) throws ArchivoException;

}