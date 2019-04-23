import java.util.*;
//NotPrim:import//

// <prim>:NotPrim ::= NOTOP
public class NotPrim extends Prim {



    public NotPrim() {

    }

    public static NotPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:NotPrim", scn$.lno);
        scn$.match(Token.Val.NOTOP, trace$);
        return new NotPrim();
    }

    public String toString() {
        return "not";
    }

    public Val apply(Val [] va) {
	boolean b0 = va[0].boolVal().val;
	return new BoolVal(!b0);
    }

    public ProcType definedType() {
        return Type.b_b;
    }

}
