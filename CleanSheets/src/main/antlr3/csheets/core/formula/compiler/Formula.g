grammar Formula;

options {
language=Java;
output=AST;
}


@parser::header {
package csheets.core.formula.compiler;
}

@lexer::header {
package csheets.core.formula.compiler;
}

// Alter code generation so catch-clauses get replace with
// this action.
@rulecatch {
	catch (RecognitionException e) {
		reportError(e);
	throw e;
	}
}

@members {
protected void mismatch(IntStream input, int ttype, BitSet follow)
throws RecognitionException
{
throw new MismatchedTokenException(ttype, input);
}

public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow)
throws RecognitionException
{
throw e;
}

@Override
protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
throw new MismatchedTokenException(ttype, input);
}
}

expression
: 	EQ! comparison EOF!
;

comparison
: 	concatenation ( ( EQ^ | NEQ^ | GT^ | LT^ | LTEQ^ | GTEQ^ ) concatenation )?
|	assignment
;

concatenation
:	arithmetic_lowest ( AMP^ arithmetic_lowest )*
;

arithmetic_lowest
:	arithmetic_low ( ( PLUS^ | MINUS^ ) arithmetic_low )*
;

arithmetic_low
:	arithmetic_medium ( ( MULTI^ | DIV^ ) arithmetic_medium )*
;

arithmetic_medium
:	arithmetic_high ( POWER^ arithmetic_high )?
;

arithmetic_high
:	arithmetic_highest ( PERCENT^ )?
;

arithmetic_highest
:	( MINUS^ )? atom
;

atom
:	function_call
|	reference
|	priority
|	literal
|	block
;

function_call
:	'FOR'^ LBRA! comparison SEMI! comparison SEMI! comparison RBRA!
|	'FOR'^ LPAR! comparison SEMI! comparison SEMI! comparison RPAR!
|	FUNCTION^ LPAR! ( comparison ( SEMI! comparison )* )? RPAR!
;

assignment
:	( CELL_REF | VART_REF | VARG_REF ) ( ASSIGN^ ) comparison
;

reference
:	CELL_REF ( ( COLON^ ) CELL_REF )?
|	VART_REF ( ( COLON^ ) VART_REF )?
|	VARG_REF ( ( COLON^ ) VARG_REF )?
;

priority
:	LPAR! comparison RPAR!
;

block
:	LBRA^ ( comparison ( SEMI! comparison )* )? RBRA!
;

literal
:	NUMBER
|	STRING
;

/* String literals, i.e. anything inside the delimiters */
FUNCTION : ( LETTER )+ ;
CELL_REF : ( ABS )? LETTER ( LETTER )? ( ABS )? ( DIGIT )+ ;
VART_REF : UNDR ( LETTER | DIGIT )+ (LSBRA DIGIT+ RSBRA)?;
VARG_REF : ARRB ( LETTER | DIGIT )+ (LSBRA DIGIT+ RSBRA)?;

/* String literals, i.e. anything inside the delimiters */
fragment LETTER : ('a'..'z'|'A'..'Z') ;
STRING	: QUOT (options {greedy=false;}:.)* QUOT  { setText(getText().substring(1, getText().length()-1)); } ;

/* Numeric literals */
fragment DIGIT : '0'..'9' ;
NUMBER : ( DIGIT )+ ( COMMA ( DIGIT )+ )? ;

/* Comparison operators */
EQ	: '=' ;
NEQ	: '<>' ;
LTEQ	: '<=' ;
GTEQ	: '>=' ;
GT	: '>' ;
LT	: '<' ;

/* Text operators */
AMP	: '&' ;
QUOT 	: '"' ;

/* Arithmetic operators */
PLUS	: '+' ;
MINUS	: '-' ;
MULTI	: '*' ;
DIV	: '/' ;
POWER	: '^' ;
PERCENT : '%' ;

/* Reference operators */
fragment ABS : '$' ;
fragment EXCL : '!' ;
fragment UNDR : '_' ;
fragment ARRB : '@' ;
COLON	: ':' ;
ASSIGN : ':=' ;

/* Miscellaneous operators */
COMMA	: ',' ;
SEMI	: ';' ;
LPAR	: '(' ;
RPAR	: ')' ;
LBRA    : '{' ;
RBRA    : '}' ;
LSBRA	: '[' ;
RSBRA	: ']' ;

/* White-space (ignored) */
WS:
( ' '
| '\r' '\n'
| '\n'
| '\t'
) {$channel=HIDDEN;}
;
