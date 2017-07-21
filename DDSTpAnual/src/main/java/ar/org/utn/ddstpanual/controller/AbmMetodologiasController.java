package ar.org.utn.ddstpanual.controller;

import org.uqbar.commons.utils.Observable;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.FiltroCreciente;
import ar.org.utn.ddstpanual.model.metodologia.FiltroDecreciente;
import ar.org.utn.ddstpanual.model.metodologia.FiltroIgual;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayor;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayorIgual;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMenor;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMenorIgual;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;

@Observable
public class AbmMetodologiasController {

  IndicadorService indicadorService;

  String nombre;
  List<Indicador> indicadores;
  Indicador indicadorCheckbox;
  List<Filtro> tiposCondiciones;
  Filtro tipoCondicionCheckbox;
  List<Condicion> condiciones;
  Integer valor;
  String error;

  public void inicializarPantalla() {
    condiciones = new ArrayList<>();
    tiposCondiciones = new ArrayList<>();
    tiposCondiciones.add(new FiltroCreciente());
    tiposCondiciones.add(new FiltroDecreciente());
    tiposCondiciones.add(new FiltroIgual());
    tiposCondiciones.add(new FiltroMayor());
    tiposCondiciones.add(new FiltroMayorIgual());
    tiposCondiciones.add(new FiltroMenor());
    tiposCondiciones.add(new FiltroMenorIgual());
    Condicion condicion = new Condicion();
    condicion.setAsd("   asdasd    ");
    condicion.setFiltro(null);
    condicion.setIndicador(null);
    condiciones.add(condicion);
  }

  public List<Indicador> obtenerIndicadores() {
    try {
      indicadores = getIndicadorService().obtenerIndicadores();
    } catch (ServiceException e) {
      error = "Error al obtener los indicadores";
    }
    return indicadores;
  }

  public List<Condicion> cargarCondicion() {
    Condicion condicion = new Condicion();
    condicion.setIndicador(indicadorCheckbox);
    Filtro filtro = tipoCondicionCheckbox;
    filtro.setValor(valor);
    condicion.setFiltro(filtro);
    condicion.setAsd("asd");
    List<Condicion> aux = new ArrayList<>();
    aux.addAll(condiciones);
    aux.add(condicion);
    condiciones.add(condicion);
    condiciones = aux;
    return condiciones;
  }

  public IndicadorService getIndicadorService() {
    if (indicadorService != null) {
      return indicadorService;
    }
    indicadorService = new IndicadorServiceImpl();
    return indicadorService;
  }


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<Indicador> getIndicadores() {
    return indicadores;
  }

  public void setIndicadores(List<Indicador> indicadores) {
    this.indicadores = indicadores;
  }

  public Indicador getIndicadorCheckbox() {
    return indicadorCheckbox;
  }

  public void setIndicadorCheckbox(Indicador indicadorCheckbox) {
    this.indicadorCheckbox = indicadorCheckbox;
  }

  public List<Filtro> getTiposCondiciones() {
    return tiposCondiciones;
  }

  public void setTiposCondiciones(List<Filtro> tiposCondiciones) {
    this.tiposCondiciones = tiposCondiciones;
  }

  public List<Condicion> getCondiciones() {
    return condiciones;
  }

  public void setCondiciones(List<Condicion> condiciones) {
    this.condiciones = condiciones;
  }

  public Filtro getTipoCondicionCheckbox() {
    return tipoCondicionCheckbox;
  }

  public void setTipoCondicionCheckbox(Filtro tipoCondicionCheckbox) {
    this.tipoCondicionCheckbox = tipoCondicionCheckbox;
  }

  public Integer getValor() {
    return valor;
  }

  public void setValor(Integer valor) {
    this.valor = valor;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}
