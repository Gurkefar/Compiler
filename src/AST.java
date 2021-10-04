public abstract class AST{
};

abstract class Expr extends AST{
    abstract public Double eval(Environment env);
}

class Addition extends Expr{
    Expr e1,e2;
    Addition(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
	return e1.eval(env)+e2.eval(env);
    }
}

class Multiplication extends Expr{
    Expr e1,e2;
    Multiplication(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
	return e1.eval(env)*e2.eval(env);
    }
}

class Subtraction extends Expr{
    Expr e1,e2;
    Subtraction(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
    return e1.eval(env)-e2.eval(env);
    }
}

class Division extends Expr{
    Expr e1,e2;
    Division(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
    return e1.eval(env)/e2.eval(env);
    }
}



class Constant extends Expr{
    Double d;
    Constant(Double d){this.d=d;}
    public Double eval(Environment env){
	return d;
    }
}

class Variable extends Expr{
    String varname;
    Variable(String varname){this.varname=varname;}
    public Double eval(Environment env){
	return env.getVariable(varname);
    }
}

class Array extends Expr{
    String arrName;
    Array(String arrName){this.arrName=arrName;}
    public Double eval(Environment env){
    return env.getVariable(arrName);
    }
}


abstract class Command extends AST{
    abstract public void eval(Environment env);
}

// Do nothing command 
class NOP extends Command{
    public void eval(Environment env){};
}

class Sequence extends Command{
    Command c1,c2;
    Sequence(Command c1, Command c2){this.c1=c1; this.c2=c2;}
    public void eval(Environment env){
	c1.eval(env);
	c2.eval(env);
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
}


class ArrAssignment extends Command{
    String s;
    Expr e;
    ArrAssignment(String s, Expr e){
    this.s = s; this.e = e;
    }
    public void eval(Environment env){
    Double d = e.eval(env);
    env.setVariable(s, d);
    //hello
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
}
class Equal extends Condition{
    Expr e1,e2;
    Equal(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env).equals(e2.eval(env));
    }
}

class Not extends Condition{
    Condition e1;
    Not(Condition e1){this.e1=e1;}
    public Boolean eval(Environment env){
    return !e1.eval(env);
    }
}
class And extends Condition{
    Condition e1,e2;
    And(Condition e1,Condition e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env) && e2.eval(env);
    }
}

class Or extends Condition{
    Condition e1,e2;
    Or(Condition e1,Condition e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env) || e2.eval(env);
    }
}
class LessThan extends Condition{
    Expr e1,e2;
    LessThan(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env) < e2.eval(env);
    }
}
class GreaterThan extends Condition{
    Expr e1,e2;
    GreaterThan(Expr e1,Expr e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
    return e1.eval(env) > e2.eval(env);
    }
}


 
//asdasd
