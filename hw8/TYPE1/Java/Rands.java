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
        case LBRACE:
        case ADD1OP:
        case LIT:
        case SET:
        case SUBOP:
        case NOTOP:
        case FALSE:
        case MULOP:
        case OROP:
        case PROC:
        case LET:
        case LEP:
        case VAR:
        case EQP:
        case GEP:
        case IF:
        case NEP:
        case DIVOP:
        case LTP:
        case ADDOP:
        case GTP:
        case TRUE:
        case DOT:
        case LETREC:
        case ZEROP:
        case ANDOP:
        case SUB1OP:
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

    public List<Val> evalRands(Env env) {
        List<Val> valList = new ArrayList<Val>();
        for (Exp e : expList)
            valList.add(e.eval(env));
        return valList;
    }

    public List<Ref> evalRandsRef(Env env) {
        List<Ref> refList = new ArrayList<Ref>();
        for (Exp e : expList)
            refList.add(new ValRef(e.eval(env)));
        return refList;
    }

    public List<Type> evalTypeRands(TypeEnv tenv) {
        List<Type> typeList = new ArrayList<Type>();
        for (Exp e : expList)
            typeList.add(e.evalType(tenv));
        return typeList;
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

}
