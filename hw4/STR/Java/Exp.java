import java.util.*;
//Exp:import//

public abstract class Exp {

    public static Exp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case STR:
            return StrExp.parse(scn$,trace$);
        case ADD1OP:
        case CONCAT:
        case SUBOP:
        case SUB1OP:
        case ADDOP:
            return PrimAppExp.parse(scn$,trace$);
        case LIT:
            return LitExp.parse(scn$,trace$);
        case VAR:
            return VarExp.parse(scn$,trace$);
        default:
            throw new RuntimeException("Exp cannot begin with " + v$);
        }
    }

    public abstract Val eval( Env env );

}
