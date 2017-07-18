package ar.org.utn.ddstpanual.service.impl;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.antlr.AntlrFormulaListener;
import ar.org.utn.ddstpanual.archivo.EmpresaArchivo;
import ar.org.utn.ddstpanual.archivo.IndicadorArchivo;
import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.utils.tree.ArbolUtil;

public class IndicadorServiceImpl implements IndicadorService {

  IndicadorArchivo indicadorArchivo;
  EmpresaArchivo empresaArchivo;
  List<FormulaIndicador> lista;

  @Override
  public void guardarIndicador(final Indicador indicador) throws ServiceException {
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
    } catch (final ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Indicador> obtenerIndicadores() throws ServiceException {
    try {
      return getIndicadorArchivo().obtenerIndicadores();
    } catch (final ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public void eliminarIndicador(final Indicador indicador) throws ServiceException {
    try {
      getIndicadorArchivo().eliminarIndicador(indicador);
    } catch (final ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public boolean validarFormula(final String formula) {
    final AntlrFormulaListener entrada = new AntlrFormulaListener();
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
  public List<FormulaIndicador> ejecutarIndicador(final String nombre, final Periodo periodo, final Empresa empresa)
      throws ServiceException {
    final List<FormulaIndicador> result = new ArrayList<FormulaIndicador>();
    try {
      final ArbolUtil arbol = new ArbolUtil();
      final String formula = obtenerFormula(nombre);
      if (periodo != null) {
        result.add(new FormulaIndicador(periodo.getFecha(), nombre, arbol.obtenerValor(formula, periodo, empresa)));
      } else {
        final List<Periodo> periodos = empresa.obtenerPeriodos();
        for (final Periodo per : periodos) {
          result.add(new FormulaIndicador(per.getFecha(), nombre, arbol.obtenerValor(formula, per, empresa)));
        }
      }
      return result;
    } catch (final ArbolException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public String obtenerFormula(final String nombre) throws ServiceException {
    try {
      return getIndicadorArchivo().obtenerFormula(nombre);
    } catch (final ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }
}
