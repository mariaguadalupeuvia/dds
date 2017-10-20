package ar.org.utn.ddstpanual.model;

import org.apache.commons.lang3.StringUtils;
import org.uqbar.commons.utils.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.tree.Arbol;
import ar.org.utn.ddstpanual.utils.tree.ArbolUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INDICADOR")
public @Data class Indicador {
  @Id
  @GeneratedValue
  private int id;

  String nombre;
  String formula;
  int usuario_id;

  @Transient
  private ArbolUtil util = new ArbolUtil();
  @Transient
  private Arbol arbol = new Arbol();


  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append(this.nombre);
    builder.append(": ");
    builder.append(this.formula);
    builder.append(": ");
    builder.append(this.usuario_id);
    return builder.toString();
  }

  public String toJson() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"nombre\" : ");
    builder.append("\"" + nombre + "\",");
    builder.append("\"formula\" : ");
    builder.append("\"" + formula + "\"");
    builder.append("\"usuario\" : ");
    builder.append("\"" + usuario_id + "\"");
    builder.append("}");
    return builder.toString();
  }

  public Indicador(String nombre, String formula, int usuario) {
    this.nombre = nombre;
    this.formula = formula;
    this.usuario_id = usuario;
  }

  public Indicador(String nombre, String formula) {
    this.nombre = nombre;
    this.formula = formula;
  }

  public void sacarEspacios() {
    nombre = StringUtils.remove(nombre, " ");
    formula = StringUtils.remove(formula, " ");
  }

  public Double ejecutarIndicador(String fecha, Empresa empresa) throws ArbolException, DbException {
    Double resultado;
    try {
      resultado = util.obtenerValor(obtenerArbol(), fecha, empresa);
    } catch (ArbolException e) {
      throw new ArbolException(e.getMessage());
    }
    return resultado;
  }

  private Arbol obtenerArbol() throws ArbolException {
    if (arbol.getRoot() == null) {
      try {
        arbol = ArbolUtil.convertFormulaToArbol(formula);
      } catch (ArbolException e) {
        throw new ArbolException(e.getMessage());
      }
    }
    return arbol;
  }

}
