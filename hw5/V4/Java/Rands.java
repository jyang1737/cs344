import java.util.*;
//Rands:import//

// <rands> **= <exp> +COMMA
public class Rands {

    public List<Exp> expList;

    public Rands(List<Exp> expList) {
        this.expList = expList;
    }

    public static Rands parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<rands>", scn$.lno);
        List<Exp> expList = new ArrayList<Exp>();
        // first trip through the parse
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case ADDOP:
        case SUBOP:
        case PROC:
        case VAR:
        case LIT:
        case DOT:
        case LET:
        case ADD1OP:
        case MULOP:
        case SUB1OP:
        case ZEROP:
        case IF:
        case DIVOP:
            while(true) {
                expList.add(Exp.parse(scn$, trace$));
                t$ = scn$.cur();
                v$ = t$.val;
                if (v$ != Token.Val.COMMA)
                    break; // not a separator, so we're done
                scn$.match(v$, trace$);
            }
        } // end of switch
        return new Rands(expList);

    }

    /**
     * Fetch the values of each expression in the parameter (opeRands) list.
     */
    public List<Val> evalRands( Env env ) {
        List<Val> args = new ArrayList<Val>();
        for ( Exp exp : expList )
            args.add( exp.eval( env ) );
        return args;
    }

    @Override
    public String toString() {
	String s = "";   // the string to return
        String sep = ""; // no separator for the first expression
	// get all of the expressions in the expression list
	for (Exp e : expList) {
	    s += sep + e;
	    sep = ",";   // commas separate the rest of the expressions
        }
        return s;
    }

}
