// Generated from formula.g4 by ANTLR 4.4
package ar.org.utn.ddstpanual.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class formulaLexer extends Lexer {
  static {
    RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION);
  }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
  public static final int SUMA = 1, RESTA = 2, MULT = 3, DIV = 4, LLAVE_IZQ = 5, LLAVE_DER = 6, COR_IZQ = 7, COR_DER = 8, PAREN_IZQ = 9,
      PAREN_DER = 10, INDICADOR = 11, CUENTA = 12, NUM = 13, WS = 14;
  public static String[] modeNames = {"DEFAULT_MODE"};

  public static final String[] tokenNames = {"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", "'\\u0006'",
      "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", "'\r'", "'\\u000E'"};
  public static final String[] ruleNames = {"SUMA", "RESTA", "MULT", "DIV", "LLAVE_IZQ", "LLAVE_DER", "COR_IZQ", "COR_DER", "PAREN_IZQ",
      "PAREN_DER", "INDICADOR", "CUENTA", "NUM", "WS"};


  public formulaLexer(final CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  @Override
  public String getGrammarFileName() {
    return "formula.g4";
  }

  @Override
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public String[] getModeNames() {
    return modeNames;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\20S\b\1\4\2\t\2\4"
      + "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"
      + "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"
      + "\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\7\f\67"
      + "\n\f\f\f\16\f:\13\f\3\f\3\f\3\r\3\r\3\r\7\rA\n\r\f\r\16\rD\13\r\3\r\3"
      + "\r\3\16\6\16I\n\16\r\16\16\16J\3\17\6\17N\n\17\r\17\16\17O\3\17\3\17\2"
      + "\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"
      + "\20\3\2\6\4\2C\\c|\6\2\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\"V\2\3\3\2"
      + "\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"
      + "\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"
      + "\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5!\3\2\2\2\7#\3\2\2\2\t%\3"
      + "\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17+\3\2\2\2\21-\3\2\2\2\23/\3\2\2\2\25"
      + "\61\3\2\2\2\27\63\3\2\2\2\31=\3\2\2\2\33H\3\2\2\2\35M\3\2\2\2\37 \7-\2"
      + "\2 \4\3\2\2\2!\"\7/\2\2\"\6\3\2\2\2#$\7,\2\2$\b\3\2\2\2%&\7\61\2\2&\n"
      + "\3\2\2\2\'(\7}\2\2(\f\3\2\2\2)*\7\177\2\2*\16\3\2\2\2+,\7]\2\2,\20\3\2"
      + "\2\2-.\7_\2\2.\22\3\2\2\2/\60\7*\2\2\60\24\3\2\2\2\61\62\7+\2\2\62\26"
      + "\3\2\2\2\63\64\5\13\6\2\648\t\2\2\2\65\67\t\3\2\2\66\65\3\2\2\2\67:\3"
      + "\2\2\28\66\3\2\2\289\3\2\2\29;\3\2\2\2:8\3\2\2\2;<\5\r\7\2<\30\3\2\2\2"
      + "=>\5\17\b\2>B\t\2\2\2?A\t\3\2\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2"
      + "\2CE\3\2\2\2DB\3\2\2\2EF\5\21\t\2F\32\3\2\2\2GI\t\4\2\2HG\3\2\2\2IJ\3"
      + "\2\2\2JH\3\2\2\2JK\3\2\2\2K\34\3\2\2\2LN\t\5\2\2ML\3\2\2\2NO\3\2\2\2O"
      + "M\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\b\17\2\2R\36\3\2\2\2\7\28BJO\3\b\2\2";
  public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}
