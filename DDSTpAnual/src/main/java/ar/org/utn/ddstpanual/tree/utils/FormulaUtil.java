package ar.org.utn.ddstpanual.tree.utils;

import java.util.Stack;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.tree.model.CuentaNode;
import ar.org.utn.ddstpanual.tree.model.IndicadorNode;
import ar.org.utn.ddstpanual.tree.model.Node;
import ar.org.utn.ddstpanual.tree.model.OperadorNode;
import ar.org.utn.ddstpanual.tree.model.ValorNode;

public class FormulaUtil {

  public static Stack<Node> inToPost(String formula) throws ServiceException {
    try {
      Stack<Node> salida = new Stack<Node>();

      // Depurar la expresion algebraica
      String expr = depurar(formula);
      String[] arrayInfix = expr.split(" ");

      // Declaración de las pilas
      Stack<String> entrada = new Stack<String>(); // Pila entrada
      Stack<String> operadores = new Stack<String>(); // Pila temporal para operadores

      // Añadir la array a la Pila de entrada
      for (int i = arrayInfix.length - 1; i >= 0; i--) {
        entrada.push(arrayInfix[i]);
      }

      // Algoritmo Infijo a Postfijo
      while (!entrada.isEmpty()) {
        switch (pref(entrada.peek())) {
          case 1:
            operadores.push(entrada.pop());
            break;
          case 3:
          case 4:
            while (pref(operadores.peek()) >= pref(entrada.peek())) {
              salida.push(new OperadorNode(operadores.pop()));
            }
            operadores.push(entrada.pop());
            break;
          case 2:
            while (!operadores.peek().equals("(")) {
              salida.push(new OperadorNode(operadores.pop()));
            }
            operadores.pop();
            entrada.pop();
            break;
          case 6:
            salida.push(new IndicadorNode(entrada.pop()));
            break;
          case 7:
            salida.push(new CuentaNode(entrada.pop()));
            break;
          default:
            salida.push(new ValorNode(entrada.pop()));
        }
      }
      return salida;
    } catch (Exception ex) {
      throw new ServiceException("Error en la expresión algebraica.");
    }
  }

  // Depurar expresión algebraica
  private static String depurar(String s) {
    s = s.replaceAll("\\s+", ""); // Elimina espacios en blanco
    s = "(" + s + ")";
    String simbols = "+-*/()";
    String str = "";

    // Deja espacios entre operadores
    for (int i = 0; i < s.length(); i++) {
      if (simbols.contains("" + s.charAt(i))) {
        str += " " + s.charAt(i) + " ";
      } else
        str += s.charAt(i);
    }
    return str.replaceAll("\\s+", " ").trim();
  }

  // Jerarquia de los operadores
  private static int pref(String op) {
    int prf = 99;
    if (op.contains("["))
      prf = 7;
    if (op.contains("{"))
      prf = 6;
    if (op.equals("^"))
      prf = 5;
    if (op.equals("*") || op.equals("/"))
      prf = 4;
    if (op.equals("+") || op.equals("-"))
      prf = 3;
    if (op.equals(")"))
      prf = 2;
    if (op.equals("("))
      prf = 1;
    return prf;
  }

}
