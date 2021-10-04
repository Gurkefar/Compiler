grammar impl;

/* A small imperative language */

start   :  cs+=command* EOF ;

program : c=command                      # SingleCommand
	| '{' cs+=command* '}'           # MultipleCommands
	;
	
command : x=ID '=' e=expr ';'	         # Assignment
	|  x = (IDARR | ('[' e=expr ']'))  '=' e=expr ';'	  		 # ArrAssignment
	| 'output' e=expr ';'            # Output
    | 'while' '('c=condition')' p=program  # WhileLoop
    | 'for' '(' index=ID '=' e1=expr '..' e2=expr ')' p=program #ForLoop
    | 'if' '(' c=condition ')' p=program #If
    | 'if' '(' c=condition ')' p1=program 'else' p2=program #IfElse
	;
	
expr: e1=expr op=('*' | '/') e2=expr     # Multiplication
	| e1=expr op=('+' | '-') e2=expr     # Addition
	| c=FLOAT     	      			     # Constant
	| x=ID		      				     # Variable
	| x = (IDARR | ('[' e=expr ']')) 	 # Array
	| '(' e=expr ')'      			     # Parenthesis
	;

condition : e1=expr '!=' e2=expr 			   # Unequal
	  	  | e1=expr '==' e2=expr 	   		   #Equal
	  	  | '!' c = condition  		 		   #Not
	  	  | c1 = condition '&&' c2 = condition #And
	  	  | c1 = condition '||' c2 = condition #Or
	  	  | e1=expr '<' e2=expr 		       #LessThan
	  	  | e1=expr '>' e2=expr 			   #GreaterThan
	  ;  


IDARR : ALPHA '[' NUM ']';
ID    : ALPHA (ALPHA|NUM)* ;
FLOAT : '-'? NUM+ ('.' NUM+)? ;
ALPHA : [a-zA-Z_ÆØÅæøå] ;
NUM   : [0-9] ;

WHITESPACE : [ \n\t\r]+ -> skip;
COMMENT    : '//'~[\n]*  -> skip;
COMMENT2   : '/*' (~[*] | '*'~[/]  )*   '*/'  -> skip;