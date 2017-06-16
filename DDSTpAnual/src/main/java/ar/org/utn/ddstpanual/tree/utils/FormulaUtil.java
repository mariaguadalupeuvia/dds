package ar.org.utn.ddstpanual.tree.utils;

import ar.org.utn.ddstpanual.tree.PilaData;

public class FormulaUtil {
  private static PilaData pilaData;
  private static String output = "";

  public static String inToPost(String formula) {
    pilaData = new PilaData(formula.length());
    for (int i = 0; i < formula.length(); i++) {
      char ch = formula.charAt(i);
      switch (ch) {
        case '+':
        case '-':
          gotOperator(ch, 1);
          break;
        case '*':
        case '/':
          gotOperator(ch, 2);
          break;
        case '(':
          pilaData.push(ch);
          break;
        case ')':
          gotParenthesis();
          break;
        default:
          output = output + ch;
      }
    }
    while (!pilaData.isEmpty())
      output = output + pilaData.pop();
    return output;
  }

  private static void gotOperator(char opThis, int prec1) {
    while (!pilaData.isEmpty()) {
      char opTop = pilaData.pop();
      if (opTop == '(') {
        pilaData.push(opTop);
        break;
      } else {
        int prec2;
        if (opTop == '+' || opTop == '-')
          prec2 = 1;
        else
          prec2 = 2;
        if (prec2 < prec1) {
          pilaData.push(opTop);
          break;
        } else
          output = output + opTop;
      }
    }
    pilaData.push(opThis);
  }

  private static void gotParenthesis() {
    while (!pilaData.isEmpty()) {
      char ch = pilaData.pop();
      if (ch == '(')
        break;
      else
        output = output + ch;
    }
  }
}
