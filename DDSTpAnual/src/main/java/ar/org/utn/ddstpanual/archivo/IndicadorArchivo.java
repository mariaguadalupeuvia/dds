package ar.org.utn.ddstpanual.archivo;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Indicador;

public interface IndicadorArchivo {

  public void guardarIndicador(Indicador indicador) throws ArchivoException;

  public List<Indicador> obtenerIndicadores() throws ArchivoException;

  public List<String> obtenerNombresIndicadores() throws ArchivoException;

}
