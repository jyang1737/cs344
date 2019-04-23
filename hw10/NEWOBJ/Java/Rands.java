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
        case VAR:
        case STRNG:
        case LANGLE:
        case LLANGLE:
        case LETREC:
        case NEP:
        case ADDFIRSTOP:
        case FIRSTOP:
        case LBRACK:
        case ERROR:
        case EQP:
        case CLASS:
        case SEND:
        case ADD1OP:
        case SUBOP:
        case IF:
        case LIT:
        case PROC:
        case LBRACE:
        case PUTC:
        case WITH:
        case SET:
        case GTP:
        case AT:
        case LEP:
        case NEWLINE:
        case NEW:
        case DISPLAY:
        case ZEROP:
        case ATAT:
        case DIVOP:
        case OBJECTP:
        case LISTP:
        case MULOP:
        case LET:
        case RESTOP:
        case NIL:
        case ADDOP:
        case DISPLAY1:
        case LENOP:
        case GEP:
        case EXIT:
        case LTP:
        case DOT:
        case SUB1OP:
        case CLASSP:
        case CHR:
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

    public List<Val> evalRands(Env env) {
        List<Val> valList = new ArrayList<Val>();
        for (Exp e : expList)
            valList.add(e.eval(env));
        return valList;
    }


}
