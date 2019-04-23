import java.util.*;
//Eval:import//

// <program>:Eval ::= <exp>
public class Eval extends Program {

    public Exp exp;

    public Eval(Exp exp) {
        this.exp = exp;
    }

    public static Eval parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<program>:Eval", scn$.lno);
        Exp exp = Exp.parse(scn$, trace$);
        return new Eval(exp);
    }


    /**
     * Evaluate this abstract syntax tree, which represents a single
     * expression.
     */
    @Override
    public String execute() {
        String evaluation = null;
        // String rendering = exp.toString(); // This gets too long!
        try {
            evaluation = exp.eval( initEnv ).toString();
        }
        catch( Exception e ) {
            evaluation = "an exception: \"" + e.getMessage() + '"';
        }
        return
           // "The code: " + rendering + '\n' + "  evaluates to " + // too long
           evaluation;
    }


}
