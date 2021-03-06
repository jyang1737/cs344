import java.util.*;
//VarExp:import//

// <exp>:VarExp ::= <VAR>
public class VarExp extends Exp {

    public Token var;

    public VarExp(Token var) {
        this.var = var;
    }

    public static VarExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:VarExp", scn$.lno);
        Token var = scn$.match(Token.Val.VAR, trace$);
        return new VarExp(var);
    }

    @Override
    public String toString() {
        return var.toString();
    }

    @Override
    public Val eval( Env env ) {
        return env.applyEnv( var.toString() );
    }

    /**
     * Variables are bound to references in the environment, so to
     * get a reference from a variable, just look it up.
     */
    @Override
    public Ref evalRef( Env env ) {
        return env.applyEnvRef( var.toString() );
    }

}
