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

    @Override
    public Val apply(Val [] vals) {
        if ( vals.length != 2 )
            throw new RuntimeException( "Two arguments expected." );
        if ( !(vals[0] instanceof IntVal) )
            throw new RuntimeException( "Type error: needed integer value." );
        if ( !(vals[1] instanceof IntVal) )
            throw new RuntimeException( "Type error: needed integer value." );
        int i0 = ((IntVal)vals[0]).v;
        int i1 = ((IntVal)vals[1]).v;
        return new IntVal( i0 - i1 );
    }

    @Override
    public String toString() {
	return "-";
    }

}
