import java.util.*;
import java.util.Arrays;

// <exp>:PrimAppExp ::= <prim> LPAREN <rands> RPAREN
public class PrimAppExp extends Exp {

    public Prim prim;
    public Rands rands;

    public PrimAppExp(Prim prim, Rands rands) {
        this.prim = prim;
        this.rands = rands;
    }

    public static PrimAppExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:PrimAppExp", scn$.lno);
        Prim prim = Prim.parse(scn$, trace$);
        scn$.match(Token.Val.LPAREN, trace$);
        Rands rands = Rands.parse(scn$, trace$);
        scn$.match(Token.Val.RPAREN, trace$);
        return new PrimAppExp(prim, rands);
    }

    public Val eval( Env env ) {
        // evaluate the terms in the expression list
        // and apply the prim to the array of Vals
        List<Val> args = rands.evalRands( env );
        Val [] va = args.toArray( new Val[ args.size() ] );
        return prim.apply( va );
    }

    public String toString() {
	return prim + "(" + rands + ")";
    }

}
