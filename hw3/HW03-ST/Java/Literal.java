import java.util.*;
//Literal:import//

// <basic>:Literal ::= <NUM>
public class Literal extends Basic {

    public Token num;

    public Literal(Token num) {
        this.num = num;
    }

    public static Literal parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<basic>:Literal", scn$.lno);
        Token num = scn$.match(Token.Val.NUM, trace$);
        return new Literal(num);
    }

    public String toString() {
        return num.str;
    }

}
