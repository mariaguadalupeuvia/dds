package ar.org.utn.ddstpanual.service;

import java.util.List;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.db.IndicadorPrecalculadoDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndicadorPrecalculadoService {

  private static EmpresaDb empresaDb = new EmpresaDb();
  private static IndicadorDb indicadorDb = new IndicadorDb();
  private static IndicadorPrecalculadoDb indicadorPrecalculadoDb = new IndicadorPrecalculadoDb();

  public void precalcularIndicadores() throws ServiceException {
    try {
      indicadorPrecalculadoDb.borrarIndicadoresPrecalculados();
      List<Empresa> empresas = empresaDb.obtenerEmpresas();
      List<Indicador> indicadores = indicadorDb.obtenerIndicadores();
      List<Periodo> periodos = empresaDb.obtenerPeriodos();
      indicadorPrecalculadoDb.precalcularIndicadores(empresas, indicadores, periodos);
    } catch (DbException e) {
      log.error(e.getMessage());
      throw new ServiceException("Se produjo un error al precalcular los indicadores.");
    }
  }

}
