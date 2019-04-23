import java.util.*;
//StrExp:import//

// <exp>:StrExp ::= <STR>
public class StrExp extends Exp {

    public Token str;

    public StrExp(Token str) {
        this.str = str;
    }

    public static StrExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:StrExp", scn$.lno);
        Token str = scn$.match(Token.Val.STR, trace$);
        return new StrExp(str);
    }

    public String toString() {
        return str.str;
    }
    public Val eval(Env env) {
        return new StrVal(str.str);
    }

}
