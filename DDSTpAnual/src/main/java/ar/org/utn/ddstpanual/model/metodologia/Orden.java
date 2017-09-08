package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.org.utn.ddstpanual.model.Indicador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDEN")
@Data
public class Orden {
  
  @Id
  @GeneratedValue
  private int id;
  
  @ManyToOne
  private Indicador indicador;

  private String tipoOrden;

  public Orden(Indicador indicador, String tipoOrden) {
    this.indicador = indicador;
    this.tipoOrden = tipoOrden;
  }

  public Indicador getIndicador() {
    return indicador;
  }

  public void setIndicador(Indicador indicador) {
    this.indicador = indicador;
  }

  public String getTipoOrden() {
    return tipoOrden;
  }

  public void setTipoOrden(String tipoOrden) {
    this.tipoOrden = tipoOrden;
  }
  
  public String toJson(){
    return "orden: {\n" + indicador.toJson() + ", tipo orden: " + tipoOrden + "}";
  }

}
