import java.util.*;
//NoArgs:import//

// <message>:NoArgs ::= <ID>
public class NoArgs extends Message {

    public Token id;

    public NoArgs(Token id) {
        this.id = id;
    }

    public static NoArgs parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<message>:NoArgs", scn$.lno);
        Token id = scn$.match(Token.Val.ID, trace$);
        return new NoArgs(id);
    }

    public String toString() {
        return "." + id.str + "()";
    }

}
