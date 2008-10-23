lexer grammar RoleExpression;
@header {
package org.springmodules.so.core.instance.permissions.evaluator;
}

T16 : '(' ;
T17 : ')' ;

// $ANTLR src "RoleExpression.g" 35
OR  	:	'||';
// $ANTLR src "RoleExpression.g" 36
AND	:	'&&';
// $ANTLR src "RoleExpression.g" 37
NOT	:	'!';
// $ANTLR src "RoleExpression.g" 38
ID	:	IDPART | BID;
// $ANTLR src "RoleExpression.g" 39
WS  	:   	(' '|'\t')+ {skip();} ;

// $ANTLR src "RoleExpression.g" 41
fragment
IDPART  :       ('a'..'z'|'A'..'Z'|'_')+ ('0'..'9')*;
// $ANTLR src "RoleExpression.g" 43
fragment
BID	:  	'[' ( ~']' )* ']' ;
