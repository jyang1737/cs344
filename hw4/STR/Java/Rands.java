import java.util.*;
import java.util.stream.Collectors;

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
        case VAR:
        case CONCAT:
        case STR:
        case SUB1OP:
        case LIT:
        case ADD1OP:
        case SUBOP:
        case ADDOP:
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
        return expList.stream()
                            .map( exp -> exp.eval(env) )
                            .collect( Collectors.toList() );
    }

    public String toString() {
        return expList.stream()
                        .map( Exp::toString )
                        .collect( Collectors.joining( "," ) );
    }

}
