import java.util.*;
//EQPrim:import//

// <prim>:EQPrim ::= EQP
public class EQPrim extends Prim {



    public EQPrim() {

    }

    public static EQPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:EQPrim", scn$.lno);
        scn$.match(Token.Val.EQP, trace$);
        return new EQPrim();
    }

    public String toString() {
        return "=?";
    }

    public Val apply(Val [] va) {
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new BoolVal(i0 == i1);
    }

    public ProcType definedType() {
        return Type.ii_b;
    }

}
