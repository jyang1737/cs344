import java.util.*;
//Expr:import//

// <expr> ::= <expr1> DOT
public class Expr {

    public Expr1 expr1;

    public Expr() { } // dummy constructor

    public Expr(Expr1 expr1) {
        this.expr1 = expr1;
    }

    public static Expr parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<expr>", scn$.lno);
        Expr1 expr1 = Expr1.parse(scn$, trace$);
        scn$.match(Token.Val.DOT, trace$);
        return new Expr(expr1);
    }

    public String toString() {
        return expr1.toString();
    }

}
