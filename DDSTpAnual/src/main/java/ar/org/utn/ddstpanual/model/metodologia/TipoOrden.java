package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
// @Entity
// @Table(name = "TIPO_ORDEN")
// @Data
public class TipoOrden {

  public static Integer ASCENDENTE = 1;
  public static Integer DESCENDENTE = 2;

  @Id
  private Integer idTipoOrden;
  private String nombreOrden;

  public Integer getIdTipoOrden() {
    return idTipoOrden;
  }

  public void setIdTipoOrden(Integer idTipoOrden) {
    this.idTipoOrden = idTipoOrden;
  }

  public String getNombreOrden() {
    return nombreOrden;
  }

  public void setNombreOrden(String nombreOrden) {
    this.nombreOrden = nombreOrden;
  }

  public String toJson() {
    return "nombre: " + nombreOrden + ", tipo: " + idTipoOrden;
  }

}
