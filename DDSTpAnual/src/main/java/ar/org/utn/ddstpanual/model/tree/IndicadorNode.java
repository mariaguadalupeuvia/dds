package ar.org.utn.ddstpanual.model.tree;

import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.db.impl.IndicadorDbImpl;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.FormulaInfinitaException;
import ar.org.utn.ddstpanual.exception.NodeException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.utils.tree.ArbolUtil;
import ar.org.utn.ddstpanual.utils.tree.IndicadorUtil;

public class IndicadorNode extends Node {

  private String nombreIndicador;
  private Arbol arbol;
  private ArbolUtil util;
  private IndicadorUtil indicadorUtil;

  public IndicadorNode(final String nombreIndicador) {
    this.setTypeNode(Node.INDICADOR);
    this.nombreIndicador = nombreIndicador;
  }

  public String getNombreIndicador() {
    return nombreIndicador;
  }

  public void setNombreIndicador(final String nombreIndicador) {
    this.nombreIndicador = nombreIndicador;
  }

  public Arbol getArbol() {
    return arbol;
  }

  public void setArbol(final Arbol arbol) {
    this.arbol = arbol;
  }

  public ArbolUtil getUtil() {
    if (util != null) {
      return util;
    } else {
      return new ArbolUtil();
    }
  }

  public IndicadorUtil getIndicadorUtil() {
    if (indicadorUtil != null) {
      return indicadorUtil;
    }
    indicadorUtil = new IndicadorUtil();
    return indicadorUtil;
  }

  @Override
  public double obtenerValor(final String fechaPeriodo, final Empresa empresa) throws NodeException {
  	Indicador indicador;
    IndicadorDb indicadorDb = new IndicadorDbImpl();
    
    try {
      indicador = indicadorDb.obtenerIndicador(nombreIndicador.substring(1, nombreIndicador.length() - 1));
      if (indicador.getFormula().equals("[]") || indicador.getFormula().isEmpty() || indicador.getFormula().equals("{}") || indicador.getFormula() == null || indicador.getFormula().contains("nombreindicador")) {
        throw new NodeException("No se encuentra la formula del indicador " + nombreIndicador + ". No puede calcularse su valor \n");
      }
      getIndicadorUtil().verificarAnalisisFormula();
      return indicador.ejecutarIndicador(fechaPeriodo, empresa);
    } catch ( FormulaInfinitaException e) {
      throw new NodeException(e.getMessage());
    } catch (DbException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
  }
}
