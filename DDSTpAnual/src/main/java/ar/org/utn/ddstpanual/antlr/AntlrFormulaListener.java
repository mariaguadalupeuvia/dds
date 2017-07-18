package ar.org.utn.ddstpanual.antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;


@SuppressWarnings("deprecation")
public class AntlrFormulaListener extends formulaBaseListener {
  public boolean validarFormula(final String formula) {

    final ANTLRInputStream in = new ANTLRInputStream(formula);
    final formulaLexer lexer = new formulaLexer(in);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final formulaParser parser = new formulaParser(tokens);

    final formulaParser.StartContext ctx = parser.start();
    return (ctx.getText().equals(formula));

  }
}
