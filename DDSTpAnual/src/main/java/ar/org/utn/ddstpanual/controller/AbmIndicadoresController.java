package ar.org.utn.ddstpanual.controller;

import org.apache.commons.lang3.StringUtils;
import org.uqbar.commons.utils.Observable;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;

@Observable
public class AbmIndicadoresController {

  private List<Indicador> indicadores;
  private String nombre;
  private String formula;
  private String error;
  private IndicadorService indicadorService;

  public void guardarIndicador() {
    error = "";
    try {
      final Indicador indicador = new Indicador(nombre, formula);
      if (validarIndicador()) {
        getIndicadorService().guardarIndicador(indicador);
        indicadores = getIndicadorService().obtenerIndicadores();
      }
    } catch (final ServiceException ex) {
      error = ex.getMessage();
    }
  }

  public void obtenerIndicadores() {
    error = "";
    try {
      indicadores = getIndicadorService().obtenerIndicadores();
    } catch (final ServiceException ex) {
      error = "Se produjo un error al obtener los indicadores.";
    }
  }

  public boolean validarIndicador() {
    boolean valido = true;
    error = "";

    nombre = StringUtils.trim(nombre);
    formula = StringUtils.trim(formula);

    if (StringUtils.isEmpty(nombre)) {
      error = "Debe ingresar un nombre.\n";
      valido = false;
    }

    if (StringUtils.contains(nombre, " ")) {
      error = "El nombre no puede contener espacios.";
      valido = false;
    }

    if (StringUtils.isEmpty(formula)) {
      error = "Debe ingresar una formula.\n";
      valido = false;
    }

    if (StringUtils.contains(formula, " ")) {
      error = "La formula no puede contener espacios.";
      valido = false;
    }

    // TODO: Validacion sobre existencia de Indicadores/Cuentas existentes
    return valido;
  }

  public IndicadorService getIndicadorService() {
    if (indicadorService != null) {
      return indicadorService;
    }
    indicadorService = new IndicadorServiceImpl();
    return indicadorService;
  }

  public void inicializarVariables() {
    nombre = "";
    formula = "";
    error = "";
    obtenerIndicadores();
  }

  public List<Indicador> getIndicadores() {
    return indicadores;
  }

  public void setIndicadores(final List<Indicador> indicadores) {
    this.indicadores = indicadores;
  }

  public String getError() {
    return error;
  }

  public void setError(final String error) {
    this.error = error;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(final String formula) {
    this.formula = formula;
  }
}
