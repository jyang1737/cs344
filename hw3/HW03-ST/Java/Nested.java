import java.util.*;
//Nested:import//

// <basic>:Nested ::= LPAREN <expr1> RPAREN
public class Nested extends Basic {

    public Expr1 expr1;

    public Nested(Expr1 expr1) {
        this.expr1 = expr1;
    }

    public static Nested parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<basic>:Nested", scn$.lno);
        scn$.match(Token.Val.LPAREN, trace$);
        Expr1 expr1 = Expr1.parse(scn$, trace$);
        scn$.match(Token.Val.RPAREN, trace$);
        return new Nested(expr1);
    }

    public String toString() {
        return expr1.toString();
    }

}
