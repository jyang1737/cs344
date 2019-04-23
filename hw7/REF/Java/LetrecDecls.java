import java.util.*;
import java.util.stream.Collectors;

// <letrecDecls> **= <VAR> EQUALS <proc>
public class LetrecDecls {

    public List<Token> varList;
    public List<Proc> procList;

    public LetrecDecls(List<Token> varList, List<Proc> procList) {
        this.varList = varList;
        this.procList = procList;
    }

    public static LetrecDecls parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<letrecDecls>", scn$.lno);
        List<Token> varList = new ArrayList<Token>();
        List<Proc> procList = new ArrayList<Proc>();
        while (true) {
            Token t$ = scn$.cur();
            Token.Val v$ = t$.val;
            switch(v$) {
            case VAR:
                varList.add(scn$.match(Token.Val.VAR, trace$));
                scn$.match(Token.Val.EQUALS, trace$);
                procList.add(Proc.parse(scn$, trace$));
                continue;
            default:
                return new LetrecDecls(varList, procList);
            }
        }

    }

    @Override
    public String toString() {
        String result = "(";
        String sep = "(";
        for ( int i = 0; i < procList.size(); ++i ) {
            result += sep + varList.get( i ) + "," + procList.get( i ) + ")";
            sep = ",(";
        }
        result += ")";
        return result;
    }


    public Env addBindings( Env enclosingEnv ) {

        EnvNode newEnv = enclosingEnv.extendEnvRef( null );
                                                // null BindingsObject
        List< Ref > refList = procList.stream()
                                .map( proc -> proc.makeClosure( newEnv ) )
                                .map( ValRef::new )
                                .collect( Collectors.toList() );
        List< String > varNameList = varList.stream()
                                .map( Token::toString )
                                .collect( Collectors.toList() );

        Bindings bObj = new Bindings( varNameList, refList);
        newEnv.replaceBindings( bObj ); // Change the null to the real thing.

        return newEnv;
}


}
