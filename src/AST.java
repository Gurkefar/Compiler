enum Type{
    BOOLTYPE, DOUBLETYPE, PROGRAM
}



class faux{ // collection of non-OO auxiliary functions (currently just error)
    public static void error(String msg){
    System.err.println("Interpreter error: "+msg);
    System.exit(-1);
    }
}

abstract class AST{
    abstract public Type typeCheck(Environment env);
}

abstract class Expr extends AST{
    abstract public Double eval(Environment env);
}

class Addition extends Expr{
    public Expr e1,e2;
    Addition(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
	return e1.eval(env)+e2.eval(env);
    }
    public Type typeCheck(Environment env){
        Type t1 = e1.typeCheck(env);
        Type t2 = e2.typeCheck(env);
        if (t1 == Type.DOUBLETYPE && t2 == Type.DOUBLETYPE){
            return Type.DOUBLETYPE;
        }
        faux.error("Addition of non-doubles");
        return null;
    }
}

class Multiplication extends Expr{
    Expr e1,e2;
    Multiplication(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
	return e1.eval(env)*e2.eval(env);
    }
    public Type typeCheck(Environment env){
        Type t1 = e1.typeCheck(env);
        Type t2 = e2.typeCheck(env);
        if (t1 == Type.DOUBLETYPE && t2 == Type.DOUBLETYPE){
            return Type.DOUBLETYPE;
        }
        faux.error("Multiplication of non-doubles");
        return null;
    }
}

class Subtraction extends Expr{
    Expr e1,e2;
    Subtraction(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
    return e1.eval(env)-e2.eval(env);
    }
    public Type typeCheck(Environment env){
        Type t1 = e1.typeCheck(env);
        Type t2 = e2.typeCheck(env);
        if (t1 == Type.DOUBLETYPE && t2 == Type.DOUBLETYPE){
            return Type.DOUBLETYPE;
        }
        faux.error("Subtraction of non-doubles");
        return null;
    }
}

class Division extends Expr{
    Expr e1,e2;
    Division(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
    return e1.eval(env)/e2.eval(env);
    }
        public Type typeCheck(Environment env){
        Type t1 = e1.typeCheck(env);
        Type t2 = e2.typeCheck(env);
        if (t1 == Type.DOUBLETYPE && t2 == Type.DOUBLETYPE){
            return Type.DOUBLETYPE;
        }
        faux.error("Division of non-doubles");
        return null;
    }
}



class Constant extends Expr{
    Double d;
    Constant(Double d){this.d=d;}
    public Double eval(Environment env){
	return d;
    }
        public Type typeCheck(Environment env){
        return Type.DOUBLETYPE;
    }
}

class Variable extends Expr{
    String varname;
    Variable(String varname){this.varname=varname;}
    public Double eval(Environment env){
	return env.getVariable(varname);
    }
        public Type typeCheck(Environment env){
        String name = env.getType(varname);
        if (name == "variable"){
            return Type.DOUBLETYPE;
        }
        else if (name == "array"){
            faux.error(varname + " was defined as array, but now used as variable");
            return Type.DOUBLETYPE;
        }
        else{
            faux.error(varname + " is not defined");
            return Type.DOUBLETYPE;
        }
    }
}

class Array extends Expr{
    String s;
    Expr e;
    Array(String s, Expr e){this.s=s; this.e=e;}
    public Double eval(Environment env){
    Double d = e.eval(env);
    return env.getVariable(s + '[' + d + ']');
    }
        public Type typeCheck(Environment env){
        String name = env.getType(s);
        if (name == "array"){
            return Type.DOUBLETYPE;
        }
        else if (name == "variable"){
            faux.error(s + " was defined as variable, but now used as array");
            return Type.DOUBLETYPE;
        }
        else{
            faux.error(s + " is not defined");
            return Type.DOUBLETYPE;
        }
    }
}

//a
abstract class Command extends AST{
    abstract public void eval(Environment env);
}

// Do nothing command 
class NOP extends Command{
    public void eval(Environment env){};
    public Type typeCheck(Environment env){return Type.PROGRAM;};
}

class Sequence extends Command{
    Command c1,c2;
    Sequence(Command c1, Command c2){this.c1=c1; this.c2=c2;}
    public void eval(Environment env){
	c1.eval(env);
	c2.eval(env);
    }
    public Type typeCheck(Environment env){
        c1.typeCheck(env);
        c2.typeCheck(env);
        return Type.PROGRAM;
    }
} 


class Assignment extends Command{
    String v;
    Expr e;
    Assignment(String v, Expr e){
	this.v=v; this.e=e;
    }
    public void eval(Environment env){
	Double d=e.eval(env);
	env.setVariable(v,d);
    }
    public Type typeCheck(Environment env){
        String type = env.getType(v);
            if (type == "array"){
                faux.error(v + " was defined as array, but now used as variable");
                return Type.DOUBLETYPE;
            }
            else{
                e.typeCheck(env);
                env.variableAssign(v);
                return Type.DOUBLETYPE; 
        }  
    }
}

//hello
class ArrAssignment extends Command{
    String s;
    Expr e2;
    Expr e1;
    ArrAssignment(String s, Expr e1, Expr e2){
    this.s = s; this.e1 = e1; this.e2 = e2;
    }
    public void eval(Environment env){
    Double d1 = e1.eval(env);
    Double d2 = e2.eval(env);
    env.setVariable(s + '[' + d1 + ']', d2);
    }
        public Type typeCheck(Environment env){
            String type = env.getType(s);
            if (type == "variable"){
                faux.error(s + " was defined as variable, but now used as array");
                return Type.DOUBLETYPE  ;
            }
            else{
                e1.typeCheck(env);
                e2.typeCheck(env);
                env.arrayAssign(s);
                return Type.DOUBLETYPE;
        }
    }
}


class Output extends Command{
    Expr e;
    Output(Expr e){
	this.e=e;
    }
    public void eval(Environment env){
	Double d=e.eval(env);
	System.out.println(d);
    }
    public Type typeCheck(Environment env){
        e.typeCheck(env);
        return Type.DOUBLETYPE;
    }
}

class While extends Command{
    Condition c;
    Command body;
    While(Condition c, Command body){
	this.c=c; this.body=body;
    }
    public void eval(Environment env){
	while (c.eval(env))
	    body.eval(env);
    }
    public Type typeCheck(Environment env){
        c.typeCheck(env);
        body.typeCheck(env);
        return Type.DOUBLETYPE;
    }
}

class For extends Command{
    Command body;
    String i;
    Expr e1;
    Expr e2;
    For(Command body, String i, Expr e1, Expr e2){
        this.body = body; this.i = i; this.e1 = e1; this.e2 = e2;
    }
    public void eval(Environment env){
        Double index = e1.eval(env);
        for (env.setVariable(i, index); index < e2.eval(env); env.setVariable(i, ++index))
            body.eval(env);
        }
    public Type typeCheck(Environment env){
    	env.variableAssign(i);
        e1.typeCheck(env);
        e2.typeCheck(env);
        body.typeCheck(env);
        return Type.DOUBLETYPE;
    }
}

class If extends Command{
    Condition c;
    Command body;
    If(Condition c, Command body){
        this.c = c;
        this.body = body;
    }

    public void eval(Environment env){
        if(c.eval(env)){
            body.eval(env);
        }
    }
    public Type typeCheck(Environment env){
        c.typeCheck(env);
        body.typeCheck(env);
        return Type.DOUBLETYPE;
    }
}
class IfElse extends Command{
    Condition c;
    Command body1;
    Command body2;
    IfElse(Condition c, Command body1, Command body2){
        this.c = c;
        this.body1 = body1;
        this.body2 = body2;
    }

    public void eval(Environment env){
        if(c.eval(env)){
            body1.eval(env);
        }
        else{
            body2.eval(env);
        }
    }
    public Type typeCheck(Environment env){
        c.typeCheck(env);
        body1.typeCheck(env);
        body2.typeCheck(env);
        return Type.DOUBLETYPE;
    }
}


abstract class Condition extends AST{
    abstract public Boolean eval(Environment env);
}

class Unequal extends Condition{
    Expr e1,e2;
    Unequal(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
	return ! e1.eval(env).equals(e2.eval(env));
    }
    public Type typeCheck(Environment env){
        return Type.BOOLTYPE;
    }
}
class Equal extends Condition{
    Expr e1,e2;
    Equal(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env).equals(e2.eval(env));
    }
    public Type typeCheck(Environment env){
        return Type.BOOLTYPE;
    }
}

class Not extends Condition{
    Condition e1;
    Not(Condition e1){this.e1=e1;}
    public Boolean eval(Environment env){
    return !e1.eval(env);
    }
    public Type typeCheck(Environment env){
        return Type.BOOLTYPE;
    }
}
class And extends Condition{
    Condition e1,e2;
    And(Condition e1,Condition e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env) && e2.eval(env);
    }
    public Type typeCheck(Environment env){
        Type t1 = e1.typeCheck(env);
        Type t2 = e2.typeCheck(env);
        if (t1 == t2){
            return Type.BOOLTYPE;
        }
        else{
            faux.error("And of non-compatible types");
            return Type.DOUBLETYPE;
        }
    }
}

class Or extends Condition{
    Condition e1,e2;
    Or(Condition e1,Condition e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env) || e2.eval(env);
    }
    public Type typeCheck(Environment env){
        Type t1 = e1.typeCheck(env);
        Type t2 = e2.typeCheck(env);
        if (t1 == t2){
            return Type.BOOLTYPE;
        }
        else{
            faux.error("Or of non-compatible types");
            return null;
        }
    }
}
class LessThan extends Condition{
    Expr e1,e2;
    LessThan(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env) < e2.eval(env);
    }
    public Type typeCheck(Environment env){
        return Type.BOOLTYPE;
    }
}
class GreaterThan extends Condition{
    Expr e1,e2;
    GreaterThan(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env) > e2.eval(env);
    }
    public Type typeCheck(Environment env){
        return Type.BOOLTYPE;
    }
}
