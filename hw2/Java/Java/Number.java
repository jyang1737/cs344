import java.util.*;
//Number:import//

// <arg>:Number ::= <NUMBER>
public class Number extends Arg {

    public Token number;

    public Number(Token number) {
        this.number = number;
    }

    public static Number parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<arg>:Number", scn$.lno);
        Token number = scn$.match(Token.Val.NUMBER, trace$);
        return new Number(number);
    }

public String toString() {
       return number.str;
}

}
