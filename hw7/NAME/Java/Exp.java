import java.util.*;
//Exp:import//

public abstract class Exp {

    public static Exp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case ADDOP:
        case SUB1OP:
        case DIVOP:
        case ZEROP:
        case MULOP:
        case ADD1OP:
        case SUBOP:
            return PrimappExp.parse(scn$,trace$);
        case LETREC:
            return LetrecExp.parse(scn$,trace$);
        case LIT:
            return LitExp.parse(scn$,trace$);
        case PROC:
            return ProcExp.parse(scn$,trace$);
        case LET:
            return LetExp.parse(scn$,trace$);
        case VAR:
            return VarExp.parse(scn$,trace$);
        case IF:
            return IfExp.parse(scn$,trace$);
        case DOT:
            return AppExp.parse(scn$,trace$);
        case LBRACE:
            return SeqExp.parse(scn$,trace$);
        case SET:
            return SetExp.parse(scn$,trace$);
        default:
            throw new RuntimeException("Exp cannot begin with " + v$);
        }
    }

    public abstract Val eval(Env env);

    // this covers all but a LitExp, ProcExp, and VarExp
    public Ref evalRef(Env env) {
        return new ThunkRef(this, env);
    }

}
