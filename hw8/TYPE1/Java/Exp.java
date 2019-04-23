import java.util.*;
//Exp:import//

public abstract class Exp {

    public static Exp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case TRUE:
            return TrueExp.parse(scn$,trace$);
        case VAR:
            return VarExp.parse(scn$,trace$);
        case LET:
            return LetExp.parse(scn$,trace$);
        case IF:
            return IfExp.parse(scn$,trace$);
        case SET:
            return SetExp.parse(scn$,trace$);
        case ADD1OP:
        case NEP:
        case SUB1OP:
        case DIVOP:
        case LTP:
        case SUBOP:
        case NOTOP:
        case ADDOP:
        case MULOP:
        case LEP:
        case GTP:
        case ZEROP:
        case ANDOP:
        case GEP:
        case EQP:
        case OROP:
            return PrimappExp.parse(scn$,trace$);
        case PROC:
            return ProcExp.parse(scn$,trace$);
        case LETREC:
            return LetrecExp.parse(scn$,trace$);
        case DOT:
            return AppExp.parse(scn$,trace$);
        case LBRACE:
            return SeqExp.parse(scn$,trace$);
        case FALSE:
            return FalseExp.parse(scn$,trace$);
        case LIT:
            return LitExp.parse(scn$,trace$);
        default:
            throw new RuntimeException("Exp cannot begin with " + v$);
        }
    }

    public abstract Val eval(Env env);
    public abstract Type evalType(TypeEnv tenv);

}
