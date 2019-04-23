import java.util.*;
//Variable:import//

// <basic>:Variable ::= <ID>
public class Variable extends Basic {

    public Token id;

    public Variable(Token id) {
        this.id = id;
    }

    public static Variable parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<basic>:Variable", scn$.lno);
        Token id = scn$.match(Token.Val.ID, trace$);
        return new Variable(id);
    }

    public String toString() {
        return id.str;
    }

}
