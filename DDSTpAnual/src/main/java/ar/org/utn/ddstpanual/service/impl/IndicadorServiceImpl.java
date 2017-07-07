package ar.org.utn.ddstpanual.service.impl;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.antlr.AntlrFormulaListener;
import ar.org.utn.ddstpanual.archivo.EmpresaArchivo;
import ar.org.utn.ddstpanual.archivo.IndicadorArchivo;
import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.FormulaInfinitaException;
import ar.org.utn.ddstpanual.exception.NoEncuentraFormulaException;
import ar.org.utn.ddstpanual.exception.NoSeEncuentraCuentaException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.tree.model.Arbol;
import ar.org.utn.ddstpanual.tree.utils.ArbolUtil;

public class IndicadorServiceImpl implements IndicadorService {

  IndicadorArchivo indicadorArchivo;
  EmpresaArchivo empresaArchivo;
  List<FormulaIndicador> lista;

  @Override
  public void guardarIndicador(Indicador indicador) throws ServiceException {
    try {
      indicador.sacarEspacios();
      if (validarFormula(indicador.getFormula())) {
        if (!getIndicadorArchivo().exists(indicador)) {
          getIndicadorArchivo().guardarIndicador(indicador);
        } else {
          throw new ServiceException("El nombre o la formula que ingreso ya existe.");
        }
      } else {
        throw new ServiceException("La formula contiene errores de sintaxis.");
      }
    } catch (ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Indicador> obtenerIndicadores() throws ServiceException {
    try {
      return getIndicadorArchivo().obtenerIndicadores();
    } catch (ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public void eliminarIndicador(Indicador indicador) throws ServiceException {
    try {
      getIndicadorArchivo().eliminarIndicador(indicador);
    } catch (ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public boolean validarFormula(String formula) {
    AntlrFormulaListener entrada = new AntlrFormulaListener();
    return entrada.validarFormula(formula);
  }

  public IndicadorArchivo getIndicadorArchivo() {
    if (indicadorArchivo != null) {
      return indicadorArchivo;
    }
    indicadorArchivo = new IndicadorArchivoImpl();
    return indicadorArchivo;
  }

  @Override
  public List<FormulaIndicador> ejecutarIndicador(String nombre, Periodo periodo, Empresa empresa) throws ArchivoException, ServiceException, ArbolException, NoEncuentraFormulaException, NoSeEncuentraCuentaException, FormulaInfinitaException {
	  List<FormulaIndicador> result = new ArrayList<FormulaIndicador>();
	  ArbolUtil arbol = new ArbolUtil();
	  String formula = obtenerFormula(nombre);
	  if(periodo != null){
		  result.add(new FormulaIndicador(periodo.getFecha(),nombre,arbol.obtenerValor(formula, periodo, empresa)));
	  }
	  else{		  
		try {
			List<Periodo> periodos = empresa.obtenerPeriodos();
			for(Periodo per : periodos){
				result.add(new FormulaIndicador(per.getFecha(),nombre,arbol.obtenerValor(formula, per, empresa)));
			}
		}
		catch (NullPointerException e){
			throw new NullPointerException(e.getMessage());
		}
	  }
	  return result;

  }

  @Override
  public String obtenerFormula(String nombre) throws ServiceException {
    try {
        return getIndicadorArchivo().obtenerFormula(nombre);
      } catch (ArchivoException e) {
        throw new ServiceException(e.getMessage());
      }
  }
}
