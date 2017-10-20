// Generated from formula.g4 by ANTLR 4.4
package ar.org.utn.ddstpanual.antlr;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class formulaParser extends Parser {
  static {
    RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION);
  }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
  public static final int SUMA = 1, RESTA = 2, MULT = 3, DIV = 4, LLAVE_IZQ = 5, LLAVE_DER = 6, COR_IZQ = 7, COR_DER = 8, PAREN_IZQ = 9,
      PAREN_DER = 10, INDICADOR = 11, CUENTA = 12, NUM = 13, WS = 14;
  public static final String[] tokenNames =
      {"<INVALID>", "'+'", "'-'", "'*'", "'/'", "'{'", "'}'", "'['", "']'", "'('", "')'", "INDICADOR", "CUENTA", "NUM", "WS"};
  public static final int RULE_start = 0, RULE_expresion = 1, RULE_operador_mult = 2, RULE_operador_aditivo = 3, RULE_operando = 4,
      RULE_suma = 5, RULE_resta = 6, RULE_mult = 7, RULE_div = 8, RULE_indicador = 9, RULE_cuenta = 10, RULE_num = 11;
  public static final String[] ruleNames =
      {"start", "expresion", "operador_mult", "operador_aditivo", "operando", "suma", "resta", "mult", "div", "indicador", "cuenta", "num"};

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
  public ATN getATN() {
    return _ATN;
  }


  List<String> cuentas = new ArrayList<String>();
  List<String> indicadores = new ArrayList<String>();

  public formulaParser(final TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  public static class StartContext extends ParserRuleContext {
    public List<Operador_aditivoContext> operador_aditivo() {
      return getRuleContexts(Operador_aditivoContext.class);
    }

    public Operador_aditivoContext operador_aditivo(final int i) {
      return getRuleContext(Operador_aditivoContext.class, i);
    }

    public List<ExpresionContext> expresion() {
      return getRuleContexts(ExpresionContext.class);
    }

    public ExpresionContext expresion(final int i) {
      return getRuleContext(ExpresionContext.class, i);
    }

    public StartContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_start;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterStart(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitStart(this);
    }
  }

  public final StartContext start() throws RecognitionException {
    final StartContext _localctx = new StartContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_start);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(24);
        expresion();
        setState(30);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == SUMA || _la == RESTA) {
          {
            {
              setState(25);
              operador_aditivo();
              setState(26);
              expresion();
            }
          }
          setState(32);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ExpresionContext extends ParserRuleContext {
    public List<OperandoContext> operando() {
      return getRuleContexts(OperandoContext.class);
    }

    public Operador_multContext operador_mult(final int i) {
      return getRuleContext(Operador_multContext.class, i);
    }

    public OperandoContext operando(final int i) {
      return getRuleContext(OperandoContext.class, i);
    }

    public List<Operador_multContext> operador_mult() {
      return getRuleContexts(Operador_multContext.class);
    }

    public ExpresionContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_expresion;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterExpresion(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitExpresion(this);
    }
  }

  public final ExpresionContext expresion() throws RecognitionException {
    final ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_expresion);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(33);
        operando();
        setState(39);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == MULT || _la == DIV) {
          {
            {
              setState(34);
              operador_mult();
              setState(35);
              operando();
            }
          }
          setState(41);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class Operador_multContext extends ParserRuleContext {
    public MultContext mult() {
      return getRuleContext(MultContext.class, 0);
    }

    public DivContext div() {
      return getRuleContext(DivContext.class, 0);
    }

    public Operador_multContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_operador_mult;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterOperador_mult(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitOperador_mult(this);
    }
  }

  public final Operador_multContext operador_mult() throws RecognitionException {
    final Operador_multContext _localctx = new Operador_multContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_operador_mult);
    try {
      setState(44);
      switch (_input.LA(1)) {
        case MULT:
          enterOuterAlt(_localctx, 1); {
          setState(42);
          mult();
        }
          break;
        case DIV:
          enterOuterAlt(_localctx, 2); {
          setState(43);
          div();
        }
          break;
        default:
          throw new NoViableAltException(this);
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class Operador_aditivoContext extends ParserRuleContext {
    public SumaContext suma() {
      return getRuleContext(SumaContext.class, 0);
    }

    public RestaContext resta() {
      return getRuleContext(RestaContext.class, 0);
    }

    public Operador_aditivoContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_operador_aditivo;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterOperador_aditivo(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitOperador_aditivo(this);
    }
  }

  public final Operador_aditivoContext operador_aditivo() throws RecognitionException {
    final Operador_aditivoContext _localctx = new Operador_aditivoContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_operador_aditivo);
    try {
      setState(48);
      switch (_input.LA(1)) {
        case SUMA:
          enterOuterAlt(_localctx, 1); {
          setState(46);
          suma();
        }
          break;
        case RESTA:
          enterOuterAlt(_localctx, 2); {
          setState(47);
          resta();
        }
          break;
        default:
          throw new NoViableAltException(this);
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class OperandoContext extends ParserRuleContext {
    public IndicadorContext indicador() {
      return getRuleContext(IndicadorContext.class, 0);
    }

    public CuentaContext cuenta() {
      return getRuleContext(CuentaContext.class, 0);
    }

    public StartContext start() {
      return getRuleContext(StartContext.class, 0);
    }

    public NumContext num() {
      return getRuleContext(NumContext.class, 0);
    }

    public TerminalNode PAREN_IZQ() {
      return getToken(formulaParser.PAREN_IZQ, 0);
    }

    public TerminalNode PAREN_DER() {
      return getToken(formulaParser.PAREN_DER, 0);
    }

    public OperandoContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_operando;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterOperando(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitOperando(this);
    }
  }

  public final OperandoContext operando() throws RecognitionException {
    final OperandoContext _localctx = new OperandoContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_operando);
    try {
      setState(57);
      switch (_input.LA(1)) {
        case INDICADOR:
          enterOuterAlt(_localctx, 1); {
          setState(50);
          indicador();
        }
          break;
        case CUENTA:
          enterOuterAlt(_localctx, 2); {
          setState(51);
          cuenta();
        }
          break;
        case NUM:
          enterOuterAlt(_localctx, 3); {
          setState(52);
          num();
        }
          break;
        case PAREN_IZQ:
          enterOuterAlt(_localctx, 4); {
          setState(53);
          match(PAREN_IZQ);
          setState(54);
          start();
          setState(55);
          match(PAREN_DER);
        }
          break;
        default:
          throw new NoViableAltException(this);
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class SumaContext extends ParserRuleContext {
    public TerminalNode SUMA() {
      return getToken(formulaParser.SUMA, 0);
    }

    public SumaContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_suma;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterSuma(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitSuma(this);
    }
  }

  public final SumaContext suma() throws RecognitionException {
    final SumaContext _localctx = new SumaContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_suma);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(59);
        match(SUMA);
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class RestaContext extends ParserRuleContext {
    public TerminalNode RESTA() {
      return getToken(formulaParser.RESTA, 0);
    }

    public RestaContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_resta;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterResta(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitResta(this);
    }
  }

  public final RestaContext resta() throws RecognitionException {
    final RestaContext _localctx = new RestaContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_resta);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(61);
        match(RESTA);
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class MultContext extends ParserRuleContext {
    public TerminalNode MULT() {
      return getToken(formulaParser.MULT, 0);
    }

    public MultContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_mult;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterMult(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitMult(this);
    }
  }

  public final MultContext mult() throws RecognitionException {
    final MultContext _localctx = new MultContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_mult);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(63);
        match(MULT);
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class DivContext extends ParserRuleContext {
    public TerminalNode DIV() {
      return getToken(formulaParser.DIV, 0);
    }

    public DivContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_div;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterDiv(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitDiv(this);
    }
  }

  public final DivContext div() throws RecognitionException {
    final DivContext _localctx = new DivContext(_ctx, getState());
    enterRule(_localctx, 16, RULE_div);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(65);
        match(DIV);
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class IndicadorContext extends ParserRuleContext {
    public Token INDICADOR;

    public TerminalNode INDICADOR() {
      return getToken(formulaParser.INDICADOR, 0);
    }

    public IndicadorContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_indicador;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterIndicador(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitIndicador(this);
    }
  }

  public final IndicadorContext indicador() throws RecognitionException {
    final IndicadorContext _localctx = new IndicadorContext(_ctx, getState());
    enterRule(_localctx, 18, RULE_indicador);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(67);
        _localctx.INDICADOR = match(INDICADOR);
        indicadores.add((_localctx.INDICADOR != null ? _localctx.INDICADOR.getText() : null).substring(1,
            (_localctx.INDICADOR != null ? _localctx.INDICADOR.getText() : null).length() - 2));
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class CuentaContext extends ParserRuleContext {
    public Token CUENTA;

    public TerminalNode CUENTA() {
      return getToken(formulaParser.CUENTA, 0);
    }

    public CuentaContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_cuenta;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterCuenta(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitCuenta(this);
    }
  }

  public final CuentaContext cuenta() throws RecognitionException {
    final CuentaContext _localctx = new CuentaContext(_ctx, getState());
    enterRule(_localctx, 20, RULE_cuenta);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(70);
        _localctx.CUENTA = match(CUENTA);
        cuentas.add((_localctx.CUENTA != null ? _localctx.CUENTA.getText() : null).substring(1,
            (_localctx.CUENTA != null ? _localctx.CUENTA.getText() : null).length() - 2));
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static class NumContext extends ParserRuleContext {
    public TerminalNode NUM() {
      return getToken(formulaParser.NUM, 0);
    }

    public NumContext(final ParserRuleContext parent, final int invokingState) {
      super(parent, invokingState);
    }

    @Override
    public int getRuleIndex() {
      return RULE_num;
    }

    @Override
    public void enterRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).enterNum(this);
    }

    @Override
    public void exitRule(final ParseTreeListener listener) {
      if (listener instanceof formulaListener)
        ((formulaListener) listener).exitNum(this);
    }
  }

  public final NumContext num() throws RecognitionException {
    final NumContext _localctx = new NumContext(_ctx, getState());
    enterRule(_localctx, 22, RULE_num);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(73);
        match(NUM);
      }
    } catch (final RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\20N\4\2\t\2\4\3\t"
      + "\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"
      + "\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\7\2\37\n\2\f\2\16\2\"\13\2\3\3\3\3\3\3"
      + "\3\3\7\3(\n\3\f\3\16\3+\13\3\3\4\3\4\5\4/\n\4\3\5\3\5\5\5\63\n\5\3\6\3"
      + "\6\3\6\3\6\3\6\3\6\3\6\5\6<\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"
      + "\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30"
      + "\2\2H\2\32\3\2\2\2\4#\3\2\2\2\6.\3\2\2\2\b\62\3\2\2\2\n;\3\2\2\2\f=\3"
      + "\2\2\2\16?\3\2\2\2\20A\3\2\2\2\22C\3\2\2\2\24E\3\2\2\2\26H\3\2\2\2\30"
      + "K\3\2\2\2\32 \5\4\3\2\33\34\5\b\5\2\34\35\5\4\3\2\35\37\3\2\2\2\36\33"
      + "\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\3\3\2\2\2\" \3\2\2\2#)\5"
      + "\n\6\2$%\5\6\4\2%&\5\n\6\2&(\3\2\2\2\'$\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)"
      + "*\3\2\2\2*\5\3\2\2\2+)\3\2\2\2,/\5\20\t\2-/\5\22\n\2.,\3\2\2\2.-\3\2\2"
      + "\2/\7\3\2\2\2\60\63\5\f\7\2\61\63\5\16\b\2\62\60\3\2\2\2\62\61\3\2\2\2"
      + "\63\t\3\2\2\2\64<\5\24\13\2\65<\5\26\f\2\66<\5\30\r\2\678\7\13\2\289\5"
      + "\2\2\29:\7\f\2\2:<\3\2\2\2;\64\3\2\2\2;\65\3\2\2\2;\66\3\2\2\2;\67\3\2"
      + "\2\2<\13\3\2\2\2=>\7\3\2\2>\r\3\2\2\2?@\7\4\2\2@\17\3\2\2\2AB\7\5\2\2"
      + "B\21\3\2\2\2CD\7\6\2\2D\23\3\2\2\2EF\7\r\2\2FG\b\13\1\2G\25\3\2\2\2HI"
      + "\7\16\2\2IJ\b\f\1\2J\27\3\2\2\2KL\7\17\2\2L\31\3\2\2\2\7 ).\62;";
  public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}
