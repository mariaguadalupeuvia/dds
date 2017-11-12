package ar.org.utn.ddstpanual.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INDICADORPRECALCULADO")
public @Data class IndicadorPrecalculado {

  @Id
  @GeneratedValue
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "empresa_id", nullable = false)
  @NonNull
  private Empresa empresa;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "indicador_id", nullable = false)
  @NonNull
  private Indicador indicador;
  @NonNull
  private String fecha;
  @NonNull
  private Double valorIndicador;
}
