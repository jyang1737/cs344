import java.util.*;
//FuncDef:import//

// <funcDef> ::= <ID> LSQBR <arg> <arglist> RSQBR
public class FuncDef {

    public Token id;
    public Arg arg;
    public Arglist arglist;

    public FuncDef() { } // dummy constructor

    public FuncDef(Token id, Arg arg, Arglist arglist) {
        this.id = id;
        this.arg = arg;
        this.arglist = arglist;
    }

    public static FuncDef parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<funcDef>", scn$.lno);
        Token id = scn$.match(Token.Val.ID, trace$);
        scn$.match(Token.Val.LSQBR, trace$);
        Arg arg = Arg.parse(scn$, trace$);
        Arglist arglist = Arglist.parse(scn$, trace$);
        scn$.match(Token.Val.RSQBR, trace$);
        return new FuncDef(id, arg, arglist);
    }

public String toString() {
       String result = arg + "." + id + "(" + arglist + ")";
       return result;
}

}
