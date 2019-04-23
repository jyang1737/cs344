import java.util.*;
//TrueExp:import//

// <exp>:TrueExp ::= TRUE
public class TrueExp extends Exp {



    public TrueExp() {

    }

    public static TrueExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:TrueExp", scn$.lno);
        scn$.match(Token.Val.TRUE, trace$);
        return new TrueExp();
    }

    public Val eval(Env env) {
        return new BoolVal(true);
    }

    public Type evalType(TypeEnv tenv) {
        return Type.boolType;
    }

}
