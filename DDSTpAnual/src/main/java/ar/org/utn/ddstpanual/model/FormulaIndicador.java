package ar.org.utn.ddstpanual.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class FormulaIndicador {

  private String nombreIndicador;
  private String formulaIndicador;
  private String periodo;
  private Double valor;

}
