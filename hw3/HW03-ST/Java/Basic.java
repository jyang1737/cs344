import java.util.*;
//Basic:import//

public abstract class Basic {

    public static Basic parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case NUM:
            return Literal.parse(scn$,trace$);
        case LPAREN:
            return Nested.parse(scn$,trace$);
        case ID:
            return Variable.parse(scn$,trace$);
        default:
            throw new RuntimeException("Basic cannot begin with " + v$);
        }
    }

    public abstract String toString();

}
