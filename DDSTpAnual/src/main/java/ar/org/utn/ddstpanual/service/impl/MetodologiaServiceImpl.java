package ar.org.utn.ddstpanual.service.impl;

import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.db.impl.MetodologiaDbImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;
import ar.org.utn.ddstpanual.service.CondicionService;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.MetodologiaService;

public class MetodologiaServiceImpl implements MetodologiaService {

  private CondicionService condicionService;
  private MetodologiaDb metododologiaDb;
  private IndicadorService indicadorService;


  @Override
  public void guardarMetodologia(Metodologia metodologia) throws ServiceException {
    try {
      getMetodologiaAlmacenamiento().guardarMetodologia(metodologia);
    } catch (ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public Metodologia obtenerMetodologia(String nombre) throws ServiceException {
    Metodologia metodologia;
    try {
      metodologia = getMetodologiaAlmacenamiento().obtenerMetodologia(nombre);
    } catch (ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
    return metodologia;
  }

  @Override
  public List<Metodologia> obtenerMetodologias() throws ServiceException {
    List<Metodologia> metodologias = null;
    try {
      metodologias = getMetodologiaAlmacenamiento().obtenerMetodologias();
    } catch (ArchivoException e) {
      doThrow(new ServiceException(e.getMessage()));
    }
    return metodologias;
  }

  @Override
  public List<Empresa> ejecutarMetodologia(List<Empresa> empresas, Metodologia metodologia, Periodo periodo) throws ServiceException {
    empresas = empresas.stream().filter(e -> {
      try {
        return getCondicionService().cumpleCondiciones(metodologia, e, periodo);
      } catch (ServiceException e1) {
        return false;
      }
    }).collect(Collectors.toList());

    Collections.sort(empresas, (e1, e2) -> {
      int comp = 0;
      try {
        comp = compareEmpresasByMetodologia(e1, e2, metodologia.getOrdenes(), periodo);
      } catch (Exception e3) {
        e3.getMessage();
      }
      return comp;
    });

    return empresas;
  }

  @Override
  public List<Condicion> agregarCondicion(List<Condicion> condiciones, Condicion condicion) throws ServiceException {
    List<Condicion> nuevasCondiciones = new ArrayList<>();
    nuevasCondiciones.addAll(condiciones);
    nuevasCondiciones.add(condicion);
    return nuevasCondiciones;
  }

  @Override
  public List<Orden> agregarOrden(List<Orden> ordenes, Orden orden) throws ServiceException {
    List<Orden> nuevosOrdenes = new ArrayList<>();
    nuevosOrdenes.addAll(ordenes);
    nuevosOrdenes.add(orden);
    return nuevosOrdenes;
  }

  @Override
  public List<Indicador> agregarIndicadorSeleccionado(List<Indicador> indicadores, Indicador indicador) throws ServiceException {
    List<Indicador> indicadoresSeleccionados = new ArrayList<>();
    indicadoresSeleccionados.addAll(indicadores);
    indicadoresSeleccionados.add(indicador);
    return indicadoresSeleccionados;
  }

  public CondicionService getCondicionService() {
    if (condicionService != null) {
      return condicionService;
    }
    condicionService = new CondicionServiceImpl();
    return condicionService;
  }

  public MetodologiaDb getMetodologiaAlmacenamiento() {
    if (metododologiaDb != null) {
      return metododologiaDb;
    }
    metododologiaDb = new MetodologiaDbImpl();// MetodologiaArchivoImpl();
    return metododologiaDb;
  }

  public IndicadorService getIndicadorService() {
    if (indicadorService != null) {
      return indicadorService;
    }
    indicadorService = new IndicadorServiceImpl();
    return indicadorService;
  }

  public int compareEmpresasByMetodologia(Empresa e1, Empresa e2, List<Orden> ordenes, Periodo per) throws ServiceException {
    int flag = 0;

    for (Orden orden : ordenes) {
      Indicador indicador = orden.getIndicador();

      Double valorE1 = getIndicadorService().ejecutarIndicador(indicador.getFormula(), per.getFecha(), e1).get(0).getValor();
      Double valorE2 = getIndicadorService().ejecutarIndicador(indicador.getFormula(), per.getFecha(), e2).get(0).getValor();

      if (orden.getTipoOrden() == "Ascendente") {
        flag = Double.compare(valorE1, valorE2);
      }

      if (orden.getTipoOrden() == "Descendente") {
        flag = Double.compare(valorE2, valorE1);
      }

      if (flag != 0) {
        break;
      }
    }
    return flag;
  }


}
