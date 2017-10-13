package ar.org.utn.ddstpanual.controller;

import org.apache.commons.lang3.StringUtils;
import org.uqbar.commons.utils.Observable;

import Repositorio.RepositorioIndicadores;

import java.util.List;

import ar.org.utn.ddstpanual.antlr.AntlrFormulaListener;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Indicador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AbmIndicadoresController {

  private List<Indicador> indicadores;
  private String nombre;
  private String formula;
  private String error;
  
  public void guardarIndicador() 
  {
      final Indicador indicador = new Indicador(nombre, formula);
      if (validarIndicador() ) 
      {
          indicador.sacarEspacios();
          if (validarFormula(indicador.getFormula())) 
          {
            if (!RepositorioIndicadores.instancia.exists(indicador)) 
            {
            	RepositorioIndicadores.instancia.guardarIndicador(indicador);
            	obtenerIndicadores();
            } 
          }
      }
  }
  
  
  public void obtenerIndicadores() {
    try {
      indicadores = RepositorioIndicadores.instancia.obtenerIndicadores();
    } catch (Exception ex) {
      error = "Se produjo un error al obtener los indicadores.";
    }
  }
  
  private boolean validarFormula(String formula) {
	    final AntlrFormulaListener entrada = new AntlrFormulaListener();
	    return entrada.validarFormula(formula);
	  }
  
  private boolean validarIndicador() {
    error = "";

    nombre = StringUtils.trim(nombre);
    formula = StringUtils.trim(formula);

    if (StringUtils.isEmpty(nombre)) {
      error = "Debe ingresar un nombre.\n";
      return false;
    }

    if (StringUtils.contains(nombre, " ")) {
      error = "El nombre no puede contener espacios.";
      return false;
    }

    if (StringUtils.isEmpty(formula)) {
      error = "Debe ingresar una formula.\n";
      return false;
    }

    if (StringUtils.contains(formula, " ")) {
      error = "La formula no puede contener espacios.";
      return false;
    }

    // TODO: Validacion sobre existencia de Indicadores/Cuentas existentes
    return true;
  }
  
  public void inicializarVariables() {
    nombre = "";
    formula = "";
    if(error == null) error = "";
    obtenerIndicadores();
  }
  
}
