import java.util.*;
//FalseExp:import//

// <exp>:FalseExp ::= FALSE
public class FalseExp extends Exp {



    public FalseExp() {

    }

    public static FalseExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:FalseExp", scn$.lno);
        scn$.match(Token.Val.FALSE, trace$);
        return new FalseExp();
    }

    public Val eval(Env env) {
        return new BoolVal(false);
    }

    public Type evalType(TypeEnv tenv) {
        return Type.boolType;
    }

}
