package ar.org.utn.ddstpanual.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import ar.org.utn.ddstpanual.archivo.IndicadorArchivo;
import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.service.IndicadorService;

public class IndicadorServiceImpl implements IndicadorService {

  IndicadorArchivo indicadorArchivo;

  @Override
  public void guardarIndicador(Indicador indicador) throws ServiceException {
    if (validarFormula(indicador.getFormula())) {
      getIndicadorArchivo().guardarIndicador(indicador);
    }
  }

  @Override
  public List<Indicador> obtenerIndicadores() throws ServiceException {
    return getIndicadorArchivo().obtenerIndicadores();
  }

  @Override
  public List<String> obtenerNombresIndicadores() throws ServiceException {
    return getIndicadorArchivo().obtenerNombresIndicadores();
  }

  @Override
  public void eliminarIndicador(Indicador indicador) throws ServiceException {

  }

  @Override
  public boolean validarFormula(String formula) {
    // TODO: Definir Regular Expression
    Pattern pattern = Pattern.compile("-?\\w+|[-+*%/()]");

    return pattern.matcher(formula).matches();

    // TODO: Validacion sobre existencia de Indicadores/Cuentas existentes
  }

  public IndicadorArchivo getIndicadorArchivo() {
    if (indicadorArchivo != null) {
      return indicadorArchivo;
    }
    indicadorArchivo = new IndicadorArchivoImpl();
    return indicadorArchivo;
  }
}
