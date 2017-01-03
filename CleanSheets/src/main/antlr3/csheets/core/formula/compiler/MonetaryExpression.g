grammar MonetaryExpression;

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
: 	HASHTAG! arithmetic_lowest EOF!
;

arithmetic_lowest
:	arithmetic_low ( ( PLUS^ | MINUS^ ) arithmetic_low )*
;

arithmetic_low
:	atom ( ( MULTI^ | DIV^ ) atom )*
;

atom
:	function_call
|	monetary
;

function_call
:	FUNCTION^ LBRA! ( arithmetic_lowest ) RBRA!
;

monetary
:       NUMBER (POUND^ | EURO^ | DOLLAR^)
;

/* String literals, i.e. anything inside the delimiters */
FUNCTION : ( LETTER )+ ;

/* String literals, i.e. anything inside the delimiters */
fragment LETTER : ('a'..'z'|'A'..'Z') ;

/* Monetary literals */
fragment DIGIT : '0'..'9' ;
NUMBER : ( DIGIT )+  ( DOT (DIGIT) (DIGIT)? )?;

/* Currency symbols */
POUND		: '\u00A3' ;
EURO		: '\u20AC' ;
DOLLAR		: '$' ;

/* Arithmetic operators */
PLUS	: '+' ;
MINUS	: '-' ;
MULTI	: '*' ;
DIV		: '/' ;

/* Miscellaneous operators */
DOT		: '.' ;
LBRA    : '{' ;
RBRA    : '}' ;
HASHTAG : '#' ;

/* White-space (ignored) */
WS:
( ' '
| '\r' '\n'
| '\n'
| '\t'
) {$channel=HIDDEN;}
;
