import java.util.*;
//MulPrim:import//

// <prim>:MulPrim ::= MULOP
public class MulPrim extends Prim {



    public MulPrim() {

    }

    public static MulPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:MulPrim", scn$.lno);
        scn$.match(Token.Val.MULOP, trace$);
        return new MulPrim();
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
        return new IntVal( i0 * i1 );
    }

    @Override
    public String toString() {
	return "*";
    }

}
