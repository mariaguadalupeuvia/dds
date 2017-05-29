package ar.org.utn.ddstpanual.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.uqbar.commons.utils.Observable;

import java.util.List;
import java.util.regex.Pattern;

import ar.org.utn.ddstpanual.archivo.IndicadorArchivo;
import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.service.IndicadorService;

@Observable
public class IndicadorServiceImpl implements IndicadorService {

  private List<Indicador> indicadores;
  private String nombre;
  private String formula;
  private String error;
  private IndicadorArchivo indicadorArchivo;

  @Override
  public void guardarIndicador() throws ServiceException {
    Indicador indicador = new Indicador(nombre, formula);
    if (validarIndicador()) {
      getIndicadorArchivo().guardarIndicador(indicador);
    }
  }

  @Override
  public List<Indicador> obtenerIndicadores() throws ServiceException {
    indicadores = getIndicadorArchivo().obtenerIndicadores();
    return indicadores;
  }

  @Override
  public void eliminarIndicador() throws ServiceException {
    // TODO Auto-generated method stub
  }

  public boolean validarIndicador() {
    boolean valido = true;
    error = "";
    // TODO: Definir Regular Expression
    Pattern pattern = Pattern.compile("-?\\w+|[-+*%/()]");

    if (StringUtils.isEmpty(nombre)) {
      error = "Debe ingresar un nombre.\n";
      valido = false;
    }

    if (StringUtils.isEmpty(formula)) {
      error = "Debe ingresar una formula.\n";
      valido = false;
    }

    if (!pattern.matcher(formula).matches()) {
      error = "La formula ingresada tiene un error de sintaxis.";
      valido = false;
    }

    // TODO: Validacion sobre existencia de Indicadores/Cuentas existentes

    return valido;
  }

  public IndicadorArchivo getIndicadorArchivo() {
    if (indicadorArchivo != null) {
      return indicadorArchivo;
    }
    indicadorArchivo = new IndicadorArchivoImpl();
    return indicadorArchivo;
  }

  public List<Indicador> getIndicadores() {
    return indicadores;
  }

  public void setIndicadores(List<Indicador> indicadores) {
    this.indicadores = indicadores;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }
}
