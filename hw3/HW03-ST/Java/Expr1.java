import java.util.*;
//Expr1:import//

// <expr1> ::= <basic> <message>
public class Expr1 {

    public Basic basic;
    public Message message;

    public Expr1(Basic basic, Message message) {
        this.basic = basic;
        this.message = message;
    }

    public static Expr1 parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<expr1>", scn$.lno);
        Basic basic = Basic.parse(scn$, trace$);
        Message message = Message.parse(scn$, trace$);
        return new Expr1(basic, message);
    }

    public String toString() {
        return basic.toString() + message.toString();
    }


}
