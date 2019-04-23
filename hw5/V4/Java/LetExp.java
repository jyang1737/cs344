import java.util.*;
//LetExp:import//

// <exp>:LetExp ::= LET <letDecls> IN <exp>
public class LetExp extends Exp {

    public LetDecls letDecls;
    public Exp exp;

    public LetExp(LetDecls letDecls, Exp exp) {
        this.letDecls = letDecls;
        this.exp = exp;
    }

    public static LetExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:LetExp", scn$.lno);
        scn$.match(Token.Val.LET, trace$);
        LetDecls letDecls = LetDecls.parse(scn$, trace$);
        scn$.match(Token.Val.IN, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new LetExp(letDecls, exp);
    }

    @Override
    public String toString() {
        String result = "let ";
        result += letDecls;
        result += " " + exp;
        return result;
    }

    @Override
    public Val eval( Env env ) {
        // Create a new set of bindings and make a new nested environment.
        Env newEnv = letDecls.addBindings( env );

        // Evaluate the final expression within that expanded environment.
        return exp.eval( newEnv );
    }

}
