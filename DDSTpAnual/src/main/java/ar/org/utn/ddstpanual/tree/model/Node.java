package ar.org.utn.ddstpanual.tree.model;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.FormulaInfinitaException;
import ar.org.utn.ddstpanual.exception.NoEncuentraFormulaException;
import ar.org.utn.ddstpanual.exception.NoSeEncuentraCuentaException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public abstract class Node {

  public static final Integer OPERADOR = 1;
  public static final Integer VALOR = 2;
  public static final Integer INDICADOR = 3;
  public static final Integer CUENTA = 4;

  private Integer typeNode;

  public abstract double obtenerValor(Periodo periodo, Empresa empresa) throws ServiceException, ArbolException, NoEncuentraFormulaException, NoSeEncuentraCuentaException, FormulaInfinitaException;

  public Integer getTypeNode() {
    return typeNode;
  }

  public void setTypeNode(Integer typeNode) {
    this.typeNode = typeNode;
  }

}
