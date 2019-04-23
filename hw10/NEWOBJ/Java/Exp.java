import java.util.*;
//Exp:import//

public abstract class Exp {

    public static Exp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case DISPLAY1:
            return Display1Exp.parse(scn$,trace$);
        case NEW:
            return NewExp.parse(scn$,trace$);
        case LIT:
            return LitExp.parse(scn$,trace$);
        case AT:
            return AtExp.parse(scn$,trace$);
        case DISPLAY:
            return DisplayExp.parse(scn$,trace$);
        case STRNG:
            return StrngExp.parse(scn$,trace$);
        case NEWLINE:
            return NewlineExp.parse(scn$,trace$);
        case WITH:
            return WithExp.parse(scn$,trace$);
        case PUTC:
            return PutcExp.parse(scn$,trace$);
        case LBRACK:
            return ListExp.parse(scn$,trace$);
        case DOT:
            return AppExp.parse(scn$,trace$);
        case ERROR:
            return ErrorExp.parse(scn$,trace$);
        case LET:
            return LetExp.parse(scn$,trace$);
        case LBRACE:
            return SeqExp.parse(scn$,trace$);
        case LETREC:
            return LetrecExp.parse(scn$,trace$);
        case NIL:
            return NilExp.parse(scn$,trace$);
        case LANGLE:
            return EnvExp.parse(scn$,trace$);
        case CHR:
            return ChrExp.parse(scn$,trace$);
        case SET:
            return SetExp.parse(scn$,trace$);
        case ATAT:
            return AtAtExp.parse(scn$,trace$);
        case EXIT:
            return ExitExp.parse(scn$,trace$);
        case PROC:
            return ProcExp.parse(scn$,trace$);
        case LLANGLE:
            return EenvExp.parse(scn$,trace$);
        case VAR:
            return VarExp.parse(scn$,trace$);
        case CLASS:
            return ClassExp.parse(scn$,trace$);
        case LENOP:
        case GEP:
        case LEP:
        case NEP:
        case LTP:
        case ADDFIRSTOP:
        case FIRSTOP:
        case ZEROP:
        case DIVOP:
        case OBJECTP:
        case LISTP:
        case CLASSP:
        case EQP:
        case SUB1OP:
        case RESTOP:
        case ADD1OP:
        case GTP:
        case MULOP:
        case ADDOP:
        case SUBOP:
            return PrimappExp.parse(scn$,trace$);
        case SEND:
            return SendExp.parse(scn$,trace$);
        case IF:
            return IfExp.parse(scn$,trace$);
        default:
            throw new RuntimeException("Exp cannot begin with " + v$);
        }
    }

    public abstract Val eval(Env env);

}
