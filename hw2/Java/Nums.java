import java.util.*;
//Nums:import//

// <nums> **= <NUMBER>
public class Nums {

    public List<Token> numberList;

    public Nums(List<Token> numberList) {
        this.numberList = numberList;
    }

    public static Nums parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<nums>", scn$.lno);
        List<Token> numberList = new ArrayList<Token>();
        while (true) {
            Token t$ = scn$.cur();
            Token.Val v$ = t$.val;
            switch(v$) {
            case NUMBER:
                numberList.add(scn$.match(Token.Val.NUMBER, trace$));
                continue;
            default:
                return new Nums(numberList);
            }
        }

    }

//Nums//

}
