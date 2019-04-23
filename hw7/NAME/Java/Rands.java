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
        case SUB1OP:
        case DOT:
        case ZEROP:
        case SUBOP:
        case PROC:
        case ADD1OP:
        case LIT:
        case LETREC:
        case VAR:
        case DIVOP:
        case LBRACE:
        case MULOP:
        case LET:
        case SET:
        case IF:
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

    // use each expression's evalRef method to build
    // a List<Ref> object
    public List<Ref> evalRandsRef(Env env) {
        List<Ref> refList = new ArrayList<Ref>();
        for (Exp e : expList)
            refList.add(e.evalRef(env));
        return refList;
    }

    public List<Val> evalRands(Env env) {
        List<Val> valList = new ArrayList<Val>();
        for (Exp e : expList)
            valList.add(e.eval(env));
        return valList;
    }


}
