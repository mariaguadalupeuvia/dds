package ar.org.utn.ddstpanual.service.impl;

import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.archivo.MetodologiaArchivo;
import ar.org.utn.ddstpanual.archivo.impl.MetodologiaArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.MetodologiaResultado;
import ar.org.utn.ddstpanual.service.CondicionService;
import ar.org.utn.ddstpanual.service.MetodologiaService;

public class MetodologiaServiceImpl implements MetodologiaService {

  private CondicionService condicionService;

  private MetodologiaArchivo metododologiaArchivo;


  @Override
  public void guardarMetodologia(Metodologia metodologia) throws ServiceException {
    try {
      getMetodologiaArchivo().guardarMetodologia(metodologia);
    } catch (ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public Metodologia obtenerMetodologia(String nombre) throws ServiceException {
    Metodologia metodologia;
    try {
      metodologia = getMetodologiaArchivo().obtenerMetodologia(nombre);
    } catch (ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
    return metodologia;
  }

  @Override
  public List<Metodologia> obtenerMetodologias() throws ServiceException {
    List<Metodologia> metodologias = null;
    try {
      metodologias = getMetodologiaArchivo().obtenerMetodologias();
    } catch (ArchivoException e) {
      doThrow(new ServiceException(e.getMessage()));
    }
    return metodologias;
  }

  @Override
  public List<MetodologiaResultado> ejecutarMetodologia(List<Empresa> empresas, Metodologia metodologia) throws ServiceException {
    List<MetodologiaResultado> resultado = new ArrayList<>();
    empresas = empresas.stream().filter(e -> {
      try {
        return getCondicionService().cumpleCondiciones(metodologia, e);
      } catch (ServiceException e1) {
        return false;
      }
    }).collect(Collectors.toList());
    return resultado;
  }

  @Override
  public List<Condicion> agregarCondicion(List<Condicion> condiciones, Condicion condicion) throws ServiceException {
    List<Condicion> nuevasCondiciones = new ArrayList<>();
    nuevasCondiciones.addAll(condiciones);
    nuevasCondiciones.add(condicion);
    return nuevasCondiciones;
  }

  public CondicionService getCondicionService() {
    if (condicionService != null) {
      return condicionService;
    }
    condicionService = new CondicionServiceImpl();
    return condicionService;
  }

  public MetodologiaArchivo getMetodologiaArchivo() {
    if (metododologiaArchivo != null) {
      return metododologiaArchivo;
    }
    metododologiaArchivo = new MetodologiaArchivoImpl();
    return metododologiaArchivo;
  }

}
