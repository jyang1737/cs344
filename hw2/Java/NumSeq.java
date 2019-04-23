import java.util.*;
//NumSeq:import//

// <numSeq> ::= LPAREN <nums> RPAREN
public class NumSeq {

    public Nums nums;

    public NumSeq() { } // dummy constructor

    public NumSeq(Nums nums) {
        this.nums = nums;
    }

    public static NumSeq parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<numSeq>", scn$.lno);
        scn$.match(Token.Val.LPAREN, trace$);
        Nums nums = Nums.parse(scn$, trace$);
        scn$.match(Token.Val.RPAREN, trace$);
        return new NumSeq(nums);
    }

//NumSeq//

}
