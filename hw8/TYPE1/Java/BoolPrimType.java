import java.util.*;
//BoolPrimType:import//

// <primType>:BoolPrimType ::= BOOL
public class BoolPrimType extends PrimType {



    public BoolPrimType() {

    }

    public static BoolPrimType parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<primType>:BoolPrimType", scn$.lno);
        scn$.match(Token.Val.BOOL, trace$);
        return new BoolPrimType();
    }

    public Type toType() {
        return new BoolType();
    }

}
