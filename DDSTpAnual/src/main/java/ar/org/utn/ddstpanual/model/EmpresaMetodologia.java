package ar.org.utn.ddstpanual.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class EmpresaMetodologia {

  private Empresa empresa;
  private List<IndicadorPrecalculado> indicadoresPrecalculados;

}
