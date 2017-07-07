package ar.org.utn.ddstpanual.tree.model;

import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.FormulaInfinitaException;
import ar.org.utn.ddstpanual.exception.NoEncuentraFormulaException;
import ar.org.utn.ddstpanual.exception.NoSeEncuentraCuentaException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;
import ar.org.utn.ddstpanual.tree.utils.ArbolUtil;
import ar.org.utn.ddstpanual.tree.utils.IndicadorUtil;

public class IndicadorNode extends Node {

  private String nombreIndicador;
  private Arbol arbol;
  private ArbolUtil util;
  private IndicadorService indicadorService;
  private IndicadorUtil indicadorUtil;

  public IndicadorNode(String nombreIndicador) {
    this.setTypeNode(Node.INDICADOR);
    this.nombreIndicador = nombreIndicador;
  }

  public String getNombreIndicador() {
    return nombreIndicador;
  }

  public void setNombreIndicador(String nombreIndicador) {
    this.nombreIndicador = nombreIndicador;
  }

  public Arbol getArbol() {
    return arbol;
  }

  public void setArbol(Arbol arbol) {
    this.arbol = arbol;
  }
  
  public ArbolUtil getUtil(){
	  if(util != null){
		  return  util;
	  }
	  else{
		  return new ArbolUtil();
	  }
  }
  
  public IndicadorService getIndicadorService(){
	  if(indicadorService != null){
		  return indicadorService;
	  }
	  else{
		  return new IndicadorServiceImpl();
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
  public double obtenerValor(Periodo periodo, Empresa empresa) throws ServiceException, ArbolException, NoEncuentraFormulaException, NoSeEncuentraCuentaException, FormulaInfinitaException {	
	 String formula = getIndicadorService().obtenerFormula(nombreIndicador.substring(1, nombreIndicador.length() - 1));
	 if(formula.equals("[]") || formula.isEmpty() || formula.equals("{}") || formula == null || formula.contains("nombreindicador")){
		 throw new NoEncuentraFormulaException("No se encuentra la formula del indicador " + nombreIndicador + ". No puede calcularse su valor \n");
	 }
	 getIndicadorUtil().verificarAnalisisFormula();
	 return getUtil().obtenerValor(formula, periodo, empresa);
  }
}
