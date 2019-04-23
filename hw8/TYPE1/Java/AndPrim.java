import java.util.*;
//AndPrim:import//

// <prim>:AndPrim ::= ANDOP
public class AndPrim extends Prim {



    public AndPrim() {

    }

    public static AndPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:AndPrim", scn$.lno);
        scn$.match(Token.Val.ANDOP, trace$);
        return new AndPrim();
    }

    public String toString() {
        return "and";
    }

    public Val apply(Val [] va) {
	boolean b0 = va[0].boolVal().val;
	boolean b1 = va[1].boolVal().val;
	return new BoolVal(b0 && b1);
    }

    public ProcType definedType() {
        return Type.bb_b;
    }

}
