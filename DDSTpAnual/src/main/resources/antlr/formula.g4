grammar formula;

@parser::header{
	import java.util.List;
}

@parser::members{
	List<String> cuentas = new ArrayList<String>();
	List<String> indicadores = new ArrayList<String>();
}


start: expresion (operador_aditivo expresion)*  EOF;

expresion: operando (operador_mult operando)*;

operador_mult : mult |
				div;
				
operador_aditivo: suma |
				  resta;
				  
operando: 
		  indicador |
		  cuenta |
		  num |
		  PAREN_IZQ start PAREN_DER ; 

suma: SUMA;

resta: RESTA;

mult: MULT;

div: DIV;

indicador: INDICADOR {indicadores.add($INDICADOR.text.subString(1,$INDICADOR.text.length() - 2));};

cuenta: CUENTA {cuentas.add($CUENTA.text.subString(1,$CUENTA.text.length() - 2));};

num: NUM;
		 
SUMA: '+';
RESTA: '-';
MULT: '*';
DIV: '/';

LLAVE_IZQ: '{';
LLAVE_DER: '}';

COR_IZQ: '[';
COR_DER: ']';

PAREN_IZQ: '(';
PAREN_DER: ')';

INDICADOR: LLAVE_IZQ [a-zA-Z][a-zA-Z0-9_]* LLAVE_DER;

CUENTA: COR_IZQ [a-zA-Z][a-zA-Z0-9_]* COR_DER;

NUM: [0-9]+;

WS :  [ \t\n\r]+  -> skip;

