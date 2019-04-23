import java.util.*;
//PrimType:import//

public abstract class PrimType {

    public static PrimType parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case INT:
            return IntPrimType.parse(scn$,trace$);
        case BOOL:
            return BoolPrimType.parse(scn$,trace$);
        default:
            throw new RuntimeException("PrimType cannot begin with " + v$);
        }
    }

    public abstract Type toType();

}
