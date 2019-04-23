import java.util.*;
//ZeropPrim:import//

// <prim>:ZeropPrim ::= ZEROP
public class ZeropPrim extends Prim {



    public ZeropPrim() {

    }

    public static ZeropPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:ZeropPrim", scn$.lno);
        scn$.match(Token.Val.ZEROP, trace$);
        return new ZeropPrim();
    }

    @Override
    public Val apply(Val [] vals) {
        if ( vals.length != 1 )
            throw new RuntimeException( "One argument expected." );
        if ( !(vals[0] instanceof IntVal) )
            throw new RuntimeException( "Type error: needed integer value." );
        int i0 = ((IntVal)vals[0]).v;
        return new IntVal( i0 == 0 ? 1 : 0 );
    }

    @Override
    public String toString() {
	return "zero?";
    }

}
