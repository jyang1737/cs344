import java.util.*;
//Program:import//

// <program> ::= <exp>
public class Program {

    public Exp exp;

    public Program() { } // dummy constructor

    public Program(Exp exp) {
        this.exp = exp;
    }

    public static Program parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<program>", scn$.lno);
        Exp exp = Exp.parse(scn$, trace$);
        return new Program(exp);
    }

    public static Env initEnv = Env.ENV_NULL.extendEnv(
            new Bindings(Arrays.asList(
                new Binding( "i", new IntVal( 1 ) ),
                new Binding( "v", new IntVal( 5 ) ),
                new Binding( "x", new IntVal( 10 ) ),
                new Binding( "l", new IntVal( 50 ) ),
                new Binding( "c", new IntVal( 100 ) ),
                new Binding( "d", new IntVal( 500 ) ),
                new Binding( "m", new IntVal( 1000 ) ),
                new Binding( "s1", new StrVal("back")),
                new Binding( "s2", new StrVal("24_for-you")),
                new Binding( "s3", new StrVal("Are_you_OK?"))
            ))
    );

    @Override
    public String toString() {
        return exp.eval( initEnv ).toString();
    }

    public String toStringOld() {
	return exp.toString();
    }

}
