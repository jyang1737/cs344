
public class ThunkRef extends Ref {

    public Exp exp;
    public Env env;

    public ThunkRef(Exp exp, Env env) {
        this.exp = exp;
        this.env = env;
    }

    public Val deRef() {
        Val v = exp.eval(env);
        // System.out.println("v=" + v);
	return v;
    }

    public Val setRef(Val v) {
	throw new RuntimeException("cannot modify a read-only expression");
    }

}