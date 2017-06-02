package ar.org.utn.ddstpanual.antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;


public class AntlrFormulaListener extends formulaBaseListener {
  public boolean validarFormula(String formula) {

    ANTLRInputStream in = new ANTLRInputStream(formula);
    formulaLexer lexer = new formulaLexer(in);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    formulaParser parser = new formulaParser(tokens);

    formulaParser.StartContext ctx = parser.start();
    return (ctx.getText().equals(formula));

  }
}
