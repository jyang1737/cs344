import java.util.*;
//Multi:import//

// <arglist>:Multi ::= COMMA <arg> <arglist>
public class Multi extends Arglist {

    public Arg arg;
    public Arglist arglist;

    public Multi(Arg arg, Arglist arglist) {
        this.arg = arg;
        this.arglist = arglist;
    }

    public static Multi parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<arglist>:Multi", scn$.lno);
        scn$.match(Token.Val.COMMA, trace$);
        Arg arg = Arg.parse(scn$, trace$);
        Arglist arglist = Arglist.parse(scn$, trace$);
        return new Multi(arg, arglist);
    }

//Multi//

}
