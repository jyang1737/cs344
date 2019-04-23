import java.util.*;
//Add1Prim:import//

// <prim>:Add1Prim ::= ADD1OP
public class Add1Prim extends Prim {



    public Add1Prim() {

    }

    public static Add1Prim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:Add1Prim", scn$.lno);
        scn$.match(Token.Val.ADD1OP, trace$);
        return new Add1Prim();
    }

    public Val apply(Val [] vals) {
        if ( vals.length != 1 )
            throw new RuntimeException( "One argument expected." );
        int i0 = vals[0].intVal();
        return new IntVal( i0 + 1 );
    }

    public String toString() {
	return "add1";
    }

}
