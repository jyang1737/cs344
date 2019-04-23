import java.util.*;

public class ProcVal extends Val {

    public Formals formals;
    public TypeExp returnTypeExp;
    public Exp body;
    public Env env;

    public ProcVal(Formals formals, TypeExp returnTypeExp, Exp body, Env env) {
	this.formals = formals;
        this.returnTypeExp = returnTypeExp;
        this.body = body;
        this.env = env;
    }

    public ProcVal procVal() {
        return this;
    }

    public Val apply(List<Ref> refList, Env e) {
        Bindings bindings = new Bindings(formals.varList, refList);
        Env nenv = env.extendEnvRef(bindings);
        return body.eval(nenv);
    }

    public String toString() {
        return "proc(" + formals + "):" + returnTypeExp.toType();
    }
}
