import java.util.*;
//LetrecDecls:import//

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

    public Env addBindings(Env env) {
        Env nenv = env.extendEnvRef(null); // no bindings yet
        List<Val> valList = new ArrayList<Val>();
        for (Proc p : procList)
            valList.add(p.makeClosure(nenv));
        List<Ref> refList = Ref.valsToRefs(valList);
        Bindings bindings = new Bindings(varList, refList);
        nenv.replaceBindings(bindings);
        return nenv;
    }

    public TypeEnv addTypeBindings(TypeEnv tenv) {
        // first check for duplicate LHS identifiers
        List<String> stringVarList = new ArrayList<String>();
        for(Token t : varList) {
            stringVarList.add(t.toString());
        }
        TypeEnv.checkDuplicates(stringVarList);
	// next get the defined type environment of the letrec,
	// ignoring the proc bodies for now
        List<Type> definedTypeList = new ArrayList<Type>();
        for (Proc p : procList)
            definedTypeList.add(p.definedType()); // just the defined type
	TypeBindings typeBindings = new TypeBindings(stringVarList,
                                                     definedTypeList);
        TypeEnv ntenv = tenv.extendTypeEnv(typeBindings);
        // finally check that the proc bodies are OK in the ntenv environment
	for (Proc p : procList)
             p.evalType(ntenv);
	return ntenv;
    }

}
