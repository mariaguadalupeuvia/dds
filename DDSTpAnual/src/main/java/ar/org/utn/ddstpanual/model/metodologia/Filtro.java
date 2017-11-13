package ar.org.utn.ddstpanual.model.metodologia;

import java.time.Year;

import ar.org.utn.ddstpanual.db.IndicadorPrecalculadoDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;

public enum Filtro {

  CRECIENTE("Estrictamente creciente"), DECRECIENTE("Estrictamente decreciente"), IGUAL("Igual"), MAYOR("Mayor"), MENOR("Menor"), MENORIGUAL("Menor Igual"), MAYORIGUAL("Mayor igual");

  private static IndicadorPrecalculadoDb indicadorPrecalculadoDb = new IndicadorPrecalculadoDb();

  private String nombre;

  Filtro(String nombre) {
    this.setNombre(nombre);
  }

  public boolean cumpleCondicion(Condicion condicion, Empresa empresa, Periodo periodo) throws FiltroException, DbException {

    Double valorIndicador = indicadorPrecalculadoDb.obtenerIndicadorPrecalculado(empresa, condicion.getIndicador(), periodo.getFecha()).getValorIndicador();
    switch (this) {
      case CRECIENTE:
        return comparadorEstricto(condicion, empresa, periodo, true);
      case DECRECIENTE:
        return comparadorEstricto(condicion, empresa, periodo, false);
      case IGUAL:
        return valorIndicador == condicion.getValor();
      case MAYOR:
        return valorIndicador > condicion.getValor();
      case MENOR:
        return valorIndicador < condicion.getValor();
      case MENORIGUAL:
        return valorIndicador <= condicion.getValor();
      case MAYORIGUAL:
        return valorIndicador >= condicion.getValor();
      default:
        throw new AssertionError("Filtro desconocido " + this);
    }
  }

  public boolean comparadorEstricto(Condicion condicion, Empresa empresa, Periodo periodo, Boolean esCreciente) throws DbException {
    Year fechaPeriodo = Year.now().minusYears(Math.round(condicion.getValor()));
    Indicador indicador = condicion.getIndicador();
    Double valorIndicadorDesde = indicadorPrecalculadoDb.obtenerIndicadorPrecalculado(empresa, indicador, fechaPeriodo.toString()).getValorIndicador();
    while (fechaPeriodo.isBefore(Year.now()) || fechaPeriodo.equals(Year.now())) {
      Double valorIndicador = indicadorPrecalculadoDb.obtenerIndicadorPrecalculado(empresa, indicador, fechaPeriodo.toString()).getValorIndicador();
      if (esCreciente) {
        if (valorIndicador < valorIndicadorDesde) {
          return false;
        }
      } else {
        if (valorIndicador > valorIndicadorDesde) {
          return false;
        }
      }
      fechaPeriodo.plusYears(1);
    }
    return true;
  }

  @Override
  public String toString() {
    return nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String toJson() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"nombre\" : ");
    builder.append("\"" + nombre + "\"");
    builder.append("}");
    return builder.toString();
  }
}
