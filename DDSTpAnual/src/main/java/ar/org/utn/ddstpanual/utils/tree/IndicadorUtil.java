package ar.org.utn.ddstpanual.utils.tree;

import ar.org.utn.ddstpanual.exception.FormulaInfinitaException;
import ar.org.utn.ddstpanual.model.tree.IndicadorNode;

public class IndicadorUtil {
  private static int contador = 0;

  public static void guardarIndicador(final IndicadorNode indicador) {

  }

  public static IndicadorNode obtenerIndicador(final String nombreIndicador) {

    return new IndicadorNode(nombreIndicador);
  }

  public static boolean existeIndicador(final String nombreIndicador) {
    return true;
  }

  public void verificarAnalisisFormula() throws FormulaInfinitaException {
    contador++;
    if (contador == 10) {
      contador = 0;
      throw new FormulaInfinitaException("No puede terminar de evaluarse el indicador. Verifique la formula");
    }
  }

}
