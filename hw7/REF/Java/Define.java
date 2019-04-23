import java.util.*;
//Define:import//

// <program>:Define ::= DEFINE <VAR> EQUALS <exp>
public class Define extends Program {

    public Token var;
    public Exp exp;

    public Define(Token var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    public static Define parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<program>:Define", scn$.lno);
        scn$.match(Token.Val.DEFINE, trace$);
        Token var = scn$.match(Token.Val.VAR, trace$);
        scn$.match(Token.Val.EQUALS, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new Define(var, exp);
    }

    /**
     * Add this object's (id,value) pair to the global environment.
     * The value's expression is evaluated based on the bindings that
     * already exist in that environment. So this is a bit weird.
     * Each DEFINE execution modifies the environment that is maintained
     * in the read-evaluate-print (Rep) form of the Parser program.
     * @return A description of the action that occurred.
     */
    @Override
    public String execute() {
        Val val = exp.eval(Program.initEnv);
	String s = var.str;
        Program.initEnv.addFirst(new Binding(s, new ValRef(val)));
	return "defined"; // return s + " set to " + val;
    }

}
