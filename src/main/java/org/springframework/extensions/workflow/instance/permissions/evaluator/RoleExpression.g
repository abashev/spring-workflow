grammar RoleExpression;

options {
    output=AST;
    ASTLabelType=CommonTree;
}
tokens {
	EXPR_DEF;
	NOT_DEF;
	ROLE_DEF;
	OR_DEF;
	AND_DEF;
}

@lexer::header {
package org.springmodules.so.core.instance.permissions.evaluator;
}
@header { 
package org.springmodules.so.core.instance.permissions.evaluator;
}

rexpr	:	expr -> ^(EXPR_DEF expr);

expr	:  	conjExpr (or^ conjExpr)*;

conjExpr:   	atom (and^ atom)*;
	
atom	:	  negative? ID -> ^(ROLE_DEF negative? ID)
		| negative^? '('! expr ')'!;
    
negative:	NOT -> ^(NOT_DEF);
and	:	AND -> ^(AND_DEF);
or	:	OR  -> ^(OR_DEF);
     
OR  	:	'||';
AND	:	'&&';
NOT	:	'!';
ID	:	IDPART | BID;
WS  	:   	(' '|'\t')+ {skip();} ;

fragment
IDPART  :       ('a'..'z'|'A'..'Z'|'_')+ ('0'..'9')*;
fragment
BID	:  	'[' ( ~']' )* ']' ;
