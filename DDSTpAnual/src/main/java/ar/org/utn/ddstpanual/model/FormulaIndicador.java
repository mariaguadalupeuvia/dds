package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
public @Data class FormulaIndicador {

  private String nombreIndicador;
  private String formulaIndicador;
  private String periodo;
  private Double valor;

}
