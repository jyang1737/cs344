import java.util.*;
//TypeExp:import//

public abstract class TypeExp {

    public static TypeExp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case LBRACK:
            return ProcTypeExp.parse(scn$,trace$);
        case INT:
        case BOOL:
            return PrimTypeExp.parse(scn$,trace$);
        default:
            throw new RuntimeException("TypeExp cannot begin with " + v$);
        }
    }

    public abstract Type toType();

}
