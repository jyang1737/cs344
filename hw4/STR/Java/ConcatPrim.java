import java.util.*;
//ConcatPrim:import//

// <prim>:ConcatPrim ::= CONCAT
public class ConcatPrim extends Prim {



    public ConcatPrim() {

    }

    public static ConcatPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:ConcatPrim", scn$.lno);
        scn$.match(Token.Val.CONCAT, trace$);
        return new ConcatPrim();
    }

    public Val apply(Val[] vals) {
        if (vals.length != 2)
            throw new RuntimeException("Two arguments expected.");
        String s0 = vals[0].strVal();
        String s1 = vals[1].strVal();
        return new StrVal(s0 + s1);
    }

}
