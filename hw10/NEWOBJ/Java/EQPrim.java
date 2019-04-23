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
        if (va.length != 2)
            throw new RuntimeException("two arguments expected");
        Val va0 = va[0];
        Val va1 = va[1];
        // if ( ( va0 instanceof IntVal ) && ( va1 instanceof IntVal ) ) {
            // int i0 = va0.intVal().val;
            // int i1 = va1.intVal().val;
            // return new IntVal(i0 == i1 ? 1 : 0);
        // }
        // else {
            return new IntVal( va0.equals( va1 ) ? 1 : 0 );
        // }
    }

}
