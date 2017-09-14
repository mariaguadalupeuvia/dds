package ar.org.utn.ddstpanual.controller;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.MetodologiaService;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;
import ar.org.utn.ddstpanual.service.impl.MetodologiaServiceImpl;

@Observable
public class AbmMetodologiasController implements WithGlobalEntityManager {

  IndicadorService indicadorService;
  MetodologiaService metodologiaService;

  String nombre;
  List<Indicador> indicadores;

  List<Indicador> indicadoresSeleccionados;

  Indicador indicadorCheckbox;
  Indicador indicadorOrdenCheckbox;

  List<Filtro> tiposCondiciones;
  List<String> tiposOrdenes;

  String tipoOrdenCheckbox;
  Filtro tipoCondicionCheckbox;

  List<Condicion> condiciones;
  List<Orden> ordenes;

  Integer valor;
  String error;



  public void inicializarVariables() {

    tiposCondiciones = new ArrayList<>();
    tiposCondiciones = entityManager().createQuery("from Filtro", Filtro.class).getResultList();
    tiposOrdenes = Arrays.asList("Ascendente", "Descendente");
    condiciones = new ArrayList<>();
    ordenes = new ArrayList<>();
    indicadores = entityManager().createQuery("from Indicador", Indicador.class).getResultList();// obtenerIndicadores();

    indicadoresSeleccionados = new ArrayList<>();

    indicadorCheckbox = null;
    indicadorOrdenCheckbox = null;
    tipoCondicionCheckbox = null;
    tipoOrdenCheckbox = "";

    nombre = "";
    valor = 0;
    error = "";
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
    try {
      Condicion condicion = new Condicion();
      condicion.setIndicador(indicadorCheckbox);
      Filtro filtro = tipoCondicionCheckbox;
      condicion.setValor(valor);
      condicion.setFiltro(filtro);
      condiciones = getMetodologiaService().agregarCondicion(condiciones, condicion);
      indicadoresSeleccionados = getMetodologiaService().agregarIndicadorSeleccionado(indicadoresSeleccionados, indicadorCheckbox);
    } catch (ServiceException e) {
      error = e.getMessage();
    }
    return condiciones;
  }

  public List<Orden> cargarOrden() {
    System.out.println(tipoOrdenCheckbox);
    try {
      Orden orden = new Orden();
      orden.setIndicador(indicadorOrdenCheckbox);

      orden.setTipoOrden(tipoOrdenCheckbox);
      ordenes = getMetodologiaService().agregarOrden(ordenes, orden);
      // indicadoresSeleccionados =
      // getMetodologiaService().agregarIndicadorSeleccionado(indicadoresSeleccionados,
      // indicadorCheckbox);
    } catch (ServiceException e) {
      error = e.getMessage();
    }
    return ordenes;
  }

  public void guardarMetodologia() {
    try {
      Metodologia metodologia = new Metodologia();
      metodologia.setCondiciones(condiciones);
      metodologia.setNombre(nombre);
      metodologia.setOrdenes(ordenes);
      // Orden orden = new Orden();
      // orden.setIndicador(indicadorOrdenCheckbox);
      // orden.setTipoOrden(tipoOrdenCheckbox);


      getMetodologiaService().guardarMetodologia(metodologia);
    } catch (ServiceException e) {
      error = e.getMessage();
    }
  }

  public IndicadorService getIndicadorService() {
    if (indicadorService != null) {
      return indicadorService;
    }
    indicadorService = new IndicadorServiceImpl();
    return indicadorService;
  }

  public MetodologiaService getMetodologiaService() {
    if (metodologiaService != null) {
      return metodologiaService;
    }
    metodologiaService = new MetodologiaServiceImpl();
    return metodologiaService;
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

  public List<Indicador> getIndicadores2() {
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

  public List<String> getTiposOrdenes() {
    return tiposOrdenes;
  }

  public void setTiposCondiciones(List<Filtro> tiposCondiciones) {
    this.tiposCondiciones = tiposCondiciones;
  }

  public void setTiposOrdenes(List<Orden> tipos) {

  }

  public List<Condicion> getCondiciones() {
    return condiciones;
  }

  public void setCondiciones(List<Condicion> condiciones) {
    this.condiciones = condiciones;
  }

  public List<Orden> getOrdenes() {
    return ordenes;
  }

  public void setOrdenes(List<Orden> ordenes) {
    this.ordenes = ordenes;
  }

  public Filtro getTipoCondicionCheckbox() {
    return tipoCondicionCheckbox;
  }

  public void setTipoCondicionCheckbox(Filtro tipoCondicionCheckbox) {
    this.tipoCondicionCheckbox = tipoCondicionCheckbox;
  }

  public String getTipoOrdenCheckbox() {
    return tipoOrdenCheckbox;
  }

  public void setTipoOrdenCheckbox(String tipo) {
    this.tipoOrdenCheckbox = tipo;
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

  public List<Indicador> getIndicadoresSeleccionados() {
    return indicadoresSeleccionados;
  }

  public void setIndicadoresSeleccionados(List<Indicador> indicadoresSeleccionados) {
    this.indicadoresSeleccionados = indicadoresSeleccionados;
  }

  public Indicador getIndicadorOrdenCheckbox() {
    return indicadorOrdenCheckbox;
  }

  public void setIndicadorOrdenCheckbox(Indicador indicadorOrdenCheckbox) {
    this.indicadorOrdenCheckbox = indicadorOrdenCheckbox;
  }


}
