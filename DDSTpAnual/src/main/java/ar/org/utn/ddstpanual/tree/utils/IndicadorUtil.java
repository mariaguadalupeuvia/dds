package ar.org.utn.ddstpanual.tree.utils;

import ar.org.utn.ddstpanual.exception.FormulaInfinitaException;
import ar.org.utn.ddstpanual.tree.model.IndicadorNode;

public class IndicadorUtil {
	private static int contador = 0;
	
	public static void guardarIndicador(IndicadorNode indicador) {

	}

	public static IndicadorNode obtenerIndicador(String nombreIndicador) {

		return new IndicadorNode(nombreIndicador);
	}

	public static boolean existeIndicador(String nombreIndicador) {
		return true;
	}

	public void verificarAnalisisFormula() throws FormulaInfinitaException{
	  contador++;
	  if(contador == 10){
		  contador = 0;
		  throw new FormulaInfinitaException("No puede terminar de evaluarse el indicador. Verifique la formula");
	  }
  }

}
