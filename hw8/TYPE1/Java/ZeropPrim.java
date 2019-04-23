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


    public String toString() {
	return "zero?";
    }

    public Val apply(Val [] va) {
	int i0 = va[0].intVal().val;
	return new BoolVal(i0 == 0);
    }

    public ProcType definedType() {
	return Type.i_b;
    }


}
