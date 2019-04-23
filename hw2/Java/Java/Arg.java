import java.util.*;
//Arg:import//

public abstract class Arg {

    public static Arg parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case NUMBER:
            return Number.parse(scn$,trace$);
        case ID:
            return Word.parse(scn$,trace$);
        default:
            throw new RuntimeException("Arg cannot begin with " + v$);
        }
    }

//Arg//

}
