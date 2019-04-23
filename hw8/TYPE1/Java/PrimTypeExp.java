import java.util.*;
//PrimTypeExp:import//

// <typeExp>:PrimTypeExp ::= <primType>
public class PrimTypeExp extends TypeExp {

    public PrimType primType;

    public PrimTypeExp(PrimType primType) {
        this.primType = primType;
    }

    public static PrimTypeExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<typeExp>:PrimTypeExp", scn$.lno);
        PrimType primType = PrimType.parse(scn$, trace$);
        return new PrimTypeExp(primType);
    }

    public Type toType() {
        return primType.toType();
    }

}
