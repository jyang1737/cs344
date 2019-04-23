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

    // No more fake assignments needed, now that we have 'let'.
    public static Env initEnv = Env.NULL;

    @Override
    public String toString() {
        String evaluation = null;
        String rendering = exp.toString();
        try {
            evaluation = exp.eval( initEnv ).toString();
        }
        catch( Exception e ) {
            evaluation = "an exception: \"" + e.getMessage() + '"';
        }
        return
           "The code: " + rendering + '\n' +
           "  evaluates to " + evaluation;
    }


}
