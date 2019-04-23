import java.util.*;
//Message:import//

public abstract class Message {

    public static Message parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case SYMBOL:
        case RPAREN:
        case DOT:
            return Args.parse(scn$,trace$);
        case ID:
            return NoArgs.parse(scn$,trace$);
        default:
            throw new RuntimeException("Message cannot begin with " + v$);
        }
    }

    public abstract String toString();

}
