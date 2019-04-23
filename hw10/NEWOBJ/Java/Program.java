import java.util.*;
//Program:import//

public abstract class Program {

    public Program() { } // dummy constructor

    public static Program parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case DEFINE:
            return Define.parse(scn$,trace$);
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
            return Eval.parse(scn$,trace$);
        default:
            throw new RuntimeException("Program cannot begin with " + v$);
        }
    }


    public static Env initEnv = Env.initEnv();


}
