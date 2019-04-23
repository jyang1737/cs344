import java.util.*;
import java.util.List;

// <exp>:AppExp ::= DOT <exp> LPAREN <rands> RPAREN
public class AppExp extends Exp {

    public Exp exp;
    public Rands rands;

    public AppExp(Exp exp, Rands rands) {
        this.exp = exp;
        this.rands = rands;
    }

    public static AppExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:AppExp", scn$.lno);
        scn$.match(Token.Val.DOT, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        scn$.match(Token.Val.LPAREN, trace$);
        Rands rands = Rands.parse(scn$, trace$);
        scn$.match(Token.Val.RPAREN, trace$);
        return new AppExp(exp, rands);
    }

    /**
     * Compute the value of a procedure call.
     */
    public Val eval( Env env ) {
        Val v = null;
        try {
            v = exp.eval( env ); // Fetch the proc itself.
            ProcVal pv = (ProcVal)v;
            List< Val > args = rands.evalRands( env );
            // Have the ProcVal execute its body.
            return pv.apply( args, env );
        }
        catch( ClassCastException cce ) {
            throw new RuntimeException( v.getClass() + " is not a proc." );
        }
    }

    @Override
    public String toString() {
        return "CALL [" + exp + "](" + rands + ")";
    }

}
