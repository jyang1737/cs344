import java.util.*;
//SubPrim:import//

// <prim>:SubPrim ::= SUBOP
public class SubPrim extends Prim {



    public SubPrim() {

    }

    public static SubPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:SubPrim", scn$.lno);
        scn$.match(Token.Val.SUBOP, trace$);
        return new SubPrim();
    }

    public Val apply(Val [] vals) {
        if ( vals.length != 2 )
            throw new RuntimeException( "Two arguments expected." );
        int i0 = vals[0].intVal();
        int i1 = vals[1].intVal();
        return new IntVal( i0 - i1 );
    }

    public String toString() {
	return "-";
    }

}
