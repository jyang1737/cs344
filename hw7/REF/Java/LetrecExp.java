import java.util.*;
//LetrecExp:import//

// <exp>:LetrecExp ::= LETREC <letrecDecls> IN <exp>
public class LetrecExp extends Exp {

    public LetrecDecls letrecDecls;
    public Exp exp;

    public LetrecExp(LetrecDecls letrecDecls, Exp exp) {
        this.letrecDecls = letrecDecls;
        this.exp = exp;
    }

    public static LetrecExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:LetrecExp", scn$.lno);
        scn$.match(Token.Val.LETREC, trace$);
        LetrecDecls letrecDecls = LetrecDecls.parse(scn$, trace$);
        scn$.match(Token.Val.IN, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new LetrecExp(letrecDecls, exp);
    }

    @Override
    public String toString() {
        String result = "let ";
        result += letrecDecls;
        result += " " + exp;
        return result;
    }

    @Override
    public Val eval( Env env ) {
        Env newEnv = letrecDecls.addBindings( env );
        return exp.eval( newEnv );
    }


}
