import java.util.*;
//DivPrim:import//

// <prim>:DivPrim ::= DIVOP
public class DivPrim extends Prim {



    public DivPrim() {

    }

    public static DivPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:DivPrim", scn$.lno);
        scn$.match(Token.Val.DIVOP, trace$);
        return new DivPrim();
    }

    @Override
    public Val apply(Val [] vals) {
        if ( vals.length != 2 )
            throw new RuntimeException( "Two arguments expected." );
        int i0 = vals[0].intVal();
        int i1 = vals[1].intVal();
        return new IntVal( i0 / i1 );
    }

    @Override
    public String toString() {
	return "/";
    }

}
