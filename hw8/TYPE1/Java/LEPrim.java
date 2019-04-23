import java.util.*;
//LEPrim:import//

// <prim>:LEPrim ::= LEP
public class LEPrim extends Prim {



    public LEPrim() {

    }

    public static LEPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:LEPrim", scn$.lno);
        scn$.match(Token.Val.LEP, trace$);
        return new LEPrim();
    }

    public String toString() {
        return "<=?";
    }

    public Val apply(Val [] va) {
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new BoolVal(i0 <= i1);
    }

    public ProcType definedType() {
        return Type.ii_b;
    }

}
