import java.util.*;
//LetDecls:import//

// <letDecls> **= <VAR> EQUALS <exp>
public class LetDecls {

    public List<Token> varList;
    public List<Exp> expList;

    public LetDecls(List<Token> varList, List<Exp> expList) {
        this.varList = varList;
        this.expList = expList;
    }

    public static LetDecls parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<letDecls>", scn$.lno);
        List<Token> varList = new ArrayList<Token>();
        List<Exp> expList = new ArrayList<Exp>();
        while (true) {
            Token t$ = scn$.cur();
            Token.Val v$ = t$.val;
            switch(v$) {
            case VAR:
                varList.add(scn$.match(Token.Val.VAR, trace$));
                scn$.match(Token.Val.EQUALS, trace$);
                expList.add(Exp.parse(scn$, trace$));
                continue;
            default:
                return new LetDecls(varList, expList);
            }
        }

    }

    @Override
    public String toString() {
        String result = "(";
        String sep = "(";
        for ( int i = 0; i < expList.size(); ++i ) {
            result += sep + varList.get( i ) + "," + expList.get( i ) + ")";
            sep = ",(";
        }
        result += ")";
        return result;
    }

    public Env addBindings( Env env ) {
        List< String > identList = new LinkedList<>();
        List< Ref > refList = new LinkedList<>();
        for ( int i = 0; i < expList.size(); ++i ) {
            identList.add( varList.get( i ).toString() );
            refList.add( expList.get( i ).evalRef( env ) );
        }
        Bindings bindings = new Bindings( identList, refList );
        return env.extendEnvRef( bindings );
    }

}
