import java.util.*;
//OrPrim:import//

// <prim>:OrPrim ::= OROP
public class OrPrim extends Prim {



    public OrPrim() {

    }

    public static OrPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:OrPrim", scn$.lno);
        scn$.match(Token.Val.OROP, trace$);
        return new OrPrim();
    }

    public String toString() {
        return "or";
    }

    public Val apply(Val [] va) {
	boolean b0 = va[0].boolVal().val;
	boolean b1 = va[1].boolVal().val;
	return new BoolVal(b0 || b1);
    }

    public ProcType definedType() {
        return Type.bb_b;
    }

}
