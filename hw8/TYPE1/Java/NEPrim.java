import java.util.*;
//NEPrim:import//

// <prim>:NEPrim ::= NEP
public class NEPrim extends Prim {



    public NEPrim() {

    }

    public static NEPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:NEPrim", scn$.lno);
        scn$.match(Token.Val.NEP, trace$);
        return new NEPrim();
    }

    public String toString() {
        return "<>?";
    }

    public Val apply(Val [] va) {
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new BoolVal(i0 != i1);
    }

    public ProcType definedType() {
        return Type.ii_b;
    }

}
