import java.util.*;
//Program:import//

public abstract class Program {

    public Program() { } // dummy constructor

    public static Program parse(Scan scn$, Trace trace$) {
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
            return Eval.parse(scn$,trace$);
        case DECLARE:
            return Declare.parse(scn$,trace$);
        case DEFINE:
            return Define.parse(scn$,trace$);
        default:
            throw new RuntimeException("Program cannot begin with " + v$);
        }
    }

    public static Env env = Env.initEnv(); // top-level value environment
    public static TypeEnv tenv = TypeEnv.initTypeEnv(); // top-level type env

}
