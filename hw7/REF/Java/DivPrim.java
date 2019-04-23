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
        if ( !(vals[0] instanceof IntVal) )
            throw new RuntimeException( "Type error: needed integer value." );
        if ( !(vals[1] instanceof IntVal) )
            throw new RuntimeException( "Type error: needed integer value." );
        int i0 = ((IntVal)vals[0]).v;
        int i1 = ((IntVal)vals[1]).v;
        return new IntVal( i0 / i1 );
    }

    @Override
    public String toString() {
	return "/";
    }

}
