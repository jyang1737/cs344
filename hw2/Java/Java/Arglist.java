import java.util.*;
//Arglist:import//

public abstract class Arglist {

    public static Arglist parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case COMMA:
            return Multi.parse(scn$,trace$);
        case RSQBR:
            return Empty.parse(scn$,trace$);
        default:
            throw new RuntimeException("Arglist cannot begin with " + v$);
        }
    }

//Arglist//

}
