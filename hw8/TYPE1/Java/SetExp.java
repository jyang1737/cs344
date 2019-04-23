import java.util.*;
//SetExp:import//

// <exp>:SetExp ::= SET <VAR> EQUALS <exp>
public class SetExp extends Exp {

    public Token var;
    public Exp exp;

    public SetExp(Token var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    public static SetExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:SetExp", scn$.lno);
        scn$.match(Token.Val.SET, trace$);
        Token var = scn$.match(Token.Val.VAR, trace$);
        scn$.match(Token.Val.EQUALS, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new SetExp(var, exp);
    }

    public Val eval(Env env) {
        Val v = exp.eval(env);
        Ref ref = env.applyEnvRef(var);
        return ref.setRef(v);
    }

    public Type evalType(TypeEnv tenv) {
        Type varType = tenv.applyTypeEnv(var.toString());
        Type expType = exp.evalType(tenv);
        Type.checkEquals(varType, expType);
        return varType;
    }


}
