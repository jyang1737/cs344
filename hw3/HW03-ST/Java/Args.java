import java.util.*;
//Args:import//

// <message>:Args ::= <argChain>
public class Args extends Message {

    public ArgChain argChain;

    public Args(ArgChain argChain) {
        this.argChain = argChain;
    }

    public static Args parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<message>:Args", scn$.lno);
        ArgChain argChain = ArgChain.parse(scn$, trace$);
        return new Args(argChain);
    }

    public String toString() {
        return argChain.toString();
    }

}
