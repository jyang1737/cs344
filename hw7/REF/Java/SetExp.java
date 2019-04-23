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


    /**
     * Return this set expression's evaluated value. As a side effect,
     * set the named (existing) variable to a new value.
     * @return the new value
     */
    @Override
    public Val eval( Env env ) {
        Val val = exp.eval( env );
        Ref ref = env.applyEnvRef( var.toString() );
        return ref.setRef( val );
    }

    @Override
    public String toString() {
        String result = var + " := " + exp;
        return result;
    }

}
