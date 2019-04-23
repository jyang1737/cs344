import java.util.*;
//Exp:import//

public abstract class Exp {

    public static Exp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case ADD1OP:
        case ADDOP:
        case MULOP:
        case SUB1OP:
        case ZEROP:
        case SUBOP:
        case DIVOP:
            return PrimAppExp.parse(scn$,trace$);
        case VAR:
            return VarExp.parse(scn$,trace$);
        case LET:
            return LetExp.parse(scn$,trace$);
        case IF:
            return IfExp.parse(scn$,trace$);
        case DOT:
            return AppExp.parse(scn$,trace$);
        case LIT:
            return LitExp.parse(scn$,trace$);
        case PROC:
            return ProcExp.parse(scn$,trace$);
        default:
            throw new RuntimeException("Exp cannot begin with " + v$);
        }
    }

    public abstract Val eval( Env env );

}
