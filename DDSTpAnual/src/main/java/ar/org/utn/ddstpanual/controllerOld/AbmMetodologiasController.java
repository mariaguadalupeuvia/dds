package ar.org.utn.ddstpanual.controllerOld;

import org.uqbar.commons.model.ObservableUtils;
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
import db.FixtureMetodologiaDB;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AbmMetodologiasController implements WithGlobalEntityManager {

  String nombre;
  List<Indicador> indicadores;
  List<Indicador> indicadores2;
  Indicador indicadorCheckbox;
  Indicador indicadorOrdenCheckbox;

  List<Filtro> tiposCondiciones;
  List<String> tiposOrdenes;

  String tipoOrdenCheckbox;
  Filtro tipoCondicionCheckbox;

  List<Condicion> condiciones;
  List<Orden> ordenes;

  Double valor;
  String error;

  public List<Condicion> cargarCondicion() {
    try {
      Condicion condicion = new Condicion();
      condicion.setIndicador(indicadorCheckbox);
      Filtro filtro = tipoCondicionCheckbox;
      condicion.setValor(valor);
      condicion.setFiltro(filtro);
      condiciones.add(condicion);
      ObservableUtils.firePropertyChanged(this, "condiciones");
    } catch (Exception e) {
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
      ordenes.add(orden);
      ObservableUtils.firePropertyChanged(this, "ordenes");
    } catch (Exception e) {
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
      metodologia.guardarMetodologia(metodologia);
    } catch (ServiceException e) {
      error = e.getMessage();
    }
  }

  public void inicializarVariables() {

    FixtureMetodologiaDB fixture = new FixtureMetodologiaDB();
    tiposCondiciones = fixture.getFiltros();
    tiposOrdenes = Arrays.asList("Ascendente", "Descendente");
    condiciones = new ArrayList<>();
    ordenes = new ArrayList<>();
    indicadores = entityManager().createQuery("from Indicador", Indicador.class).getResultList();
    indicadores2 = indicadores;
    indicadorCheckbox = null;
    indicadorOrdenCheckbox = null;
    tipoCondicionCheckbox = null;
    tipoOrdenCheckbox = null;

    nombre = "";
    valor = 0.0;
    error = "";
  }
}
