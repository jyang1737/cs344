import java.util.*;
//Program:import//

public abstract class Program {

    public Program() { } // dummy constructor

    public static Program parse(Scan scn$, Trace trace$) {
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
            return Eval.parse(scn$,trace$);
        case DEFINE:
            return Define.parse(scn$,trace$);
        default:
            throw new RuntimeException("Program cannot begin with " + v$);
        }
    }

    public static Env env = Env.initEnv(); // the initial environment

}
