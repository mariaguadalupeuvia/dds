package ar.org.utn.ddstpanual.service.impl;

import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.db.impl.MetodologiaDbImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.CondicionException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
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
  public List<Empresa> ejecutarMetodologia(List<Empresa> empresas, List<Condicion> condiciones, Periodo periodo)
  {
  	  int contadores[] = new int[empresas.size()];
  	  
  	  condiciones.stream().forEach(condicion-> {
		try {
			aplicarCondicion(empresas, periodo, condicion, contadores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}); 
	return ordenarEmpresas(empresas, contadores);
  }

  
	private void aplicarCondicion(List<Empresa> empresas, Periodo periodo, Condicion condicion, int[] contadores) throws CondicionException 
	{
		int i = 0;
	    for (Empresa empresa : empresas) 
	    {
	        if (condicion.cumpleCondicion(empresa, periodo)) 
	        {
	          contadores[i]++;
	        }
	        i++;
	      }
	}

  private List<Empresa> ordenarEmpresas(List<Empresa> empresas, int contadores[]) 
  {
	  return empresas.stream().sorted((e1, e2) -> 
	  	Integer.compare(contadores[empresas.indexOf(e1)], contadores[empresas.indexOf(e2)])).collect(Collectors.toList());
}

@Override
  public List<Condicion> agregarCondicion(List<Condicion> condiciones, Condicion condicion) throws ServiceException {
    List<Condicion> nuevasCondiciones = new ArrayList<>();
    nuevasCondiciones.addAll(condiciones);
    nuevasCondiciones.add(condicion);
    return nuevasCondiciones;
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

//  public int compareEmpresasByMetodologia(Empresa e1, Empresa e2, Orden orden, Periodo per) throws ServiceException {
//    int flag = 0;
//
//    Indicador indicador = orden.getIndicador();
//
//    Double valorE1 = getIndicadorService().ejecutarIndicador(indicador.getFormula(), per.getFecha(), e1).get(0).getValor();
//    Double valorE2 = getIndicadorService().ejecutarIndicador(indicador.getFormula(), per.getFecha(), e2).get(0).getValor();
//
//    if (orden.getTipoOrden().getIdTipoOrden() == TipoOrden.ASCENDENTE) {
//      return Double.compare(valorE1, valorE2);
//    }
//
//    if (orden.getTipoOrden().getIdTipoOrden() == TipoOrden.DESCENDENTE) {
//      return Double.compare(valorE2, valorE1);
//    }
//
//    return flag;
//  }

}
