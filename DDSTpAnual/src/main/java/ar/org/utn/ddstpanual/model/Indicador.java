package ar.org.utn.ddstpanual.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.uqbar.commons.utils.Observable;

import ar.org.utn.ddstpanual.exception.ArbolException;
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

  @Transient
  private ArbolUtil util = new ArbolUtil();
  @Transient
  private Arbol arbol = new Arbol();
  
  public void sacarEspacios() {
    nombre = StringUtils.remove(nombre, " ");
    formula = StringUtils.remove(formula, " ");
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append(this.nombre);
    builder.append(": ");
    builder.append(this.formula);
    return builder.toString();
  }

  public String toJson() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"nombre\" : ");
    builder.append("\"" + nombre + "\",");
    builder.append("\"formula\" : ");
    builder.append("\"" + formula + "\"");
    builder.append("}");
    return builder.toString();
  }

  public Indicador(String nombre, String formula) {
    this.nombre = nombre;
    this.formula = formula;
  }

	public Double ejecutarIndicador(String fecha, Empresa empresa) 
	{
		try 
		{
			return  util.obtenerValor(getArbol(), fecha, empresa);
		} catch (ArbolException e) 
		{
			return 0.0;
		}
	}
	
	private Arbol getArbol() {
		if(arbol.getRoot() == null)
			try {
				arbol = ArbolUtil.convertFormulaToArbol(formula);
			} catch (ArbolException e) {
				return null;
			}
		return arbol;
	}
	
}
