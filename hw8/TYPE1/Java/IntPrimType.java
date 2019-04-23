import java.util.*;
//IntPrimType:import//

// <primType>:IntPrimType ::= INT
public class IntPrimType extends PrimType {



    public IntPrimType() {

    }

    public static IntPrimType parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<primType>:IntPrimType", scn$.lno);
        scn$.match(Token.Val.INT, trace$);
        return new IntPrimType();
    }

    public Type toType() {
        return new IntType();
    }

}
