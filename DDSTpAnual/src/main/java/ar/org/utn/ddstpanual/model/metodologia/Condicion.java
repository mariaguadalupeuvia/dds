package ar.org.utn.ddstpanual.model.metodologia;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.uqbar.commons.utils.Observable;

import ar.org.utn.ddstpanual.exception.CondicionException;
import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONDICIONES")
@Data public class Condicion 
{

  @Id
  @GeneratedValue
  private long id;
  private Integer valor;
  @ManyToOne
  @Cascade(value=CascadeType.ALL)
  private Indicador indicador;
  @ManyToOne
  @Cascade(value=CascadeType.ALL)
  private Filtro filtro;
 

  public Condicion(Indicador indicador, Filtro filtro, Integer valor)
  {
	  this.indicador = indicador;
	  this.valor = valor;
	  this.filtro = filtro;
  }
  
  public boolean cumpleCondicion(Empresa empresa, Periodo periodo) throws CondicionException {
    try {
      return filtro.cumpleCondicion(this, empresa, periodo);
    } catch (FiltroException e) {
      throw new CondicionException(e.getMessage());
    }
  }

  public String toJson() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"indicador\" : ");
    builder.append(indicador.toJson() + ",");
    builder.append("\"valor\" : ");
    builder.append("\"" + valor + "\"");
    builder.append("\"filtro\" : ");
    builder.append(filtro.toJson());
    builder.append("}");
    return builder.toString();
  }

}
