import java.util.*;
//Formals:import//

// <formals> **= <VAR> COLON <typeExp> +COMMA
public class Formals {

    public List<Token> varList;
    public List<TypeExp> typeExpList;

    public Formals(List<Token> varList, List<TypeExp> typeExpList) {
        this.varList = varList;
        this.typeExpList = typeExpList;
    }

    public static Formals parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<formals>", scn$.lno);
        List<Token> varList = new ArrayList<Token>();
        List<TypeExp> typeExpList = new ArrayList<TypeExp>();
        // first trip through the parse
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case VAR:
            while(true) {
                varList.add(scn$.match(Token.Val.VAR, trace$));
                scn$.match(Token.Val.COLON, trace$);
                typeExpList.add(TypeExp.parse(scn$, trace$));
                t$ = scn$.cur();
                v$ = t$.val;
                if (v$ != Token.Val.COMMA)
                    break; // not a separator, so we're done
                scn$.match(v$, trace$);
            }
        } // end of switch
        return new Formals(varList, typeExpList);

    }


    public List<Type> formalTypeList() {
        List<String> stringVarList = new ArrayList<String>();
        for(Token t : varList) {
          stringVarList.add(t.toString());
        }
        TypeEnv.checkDuplicates(stringVarList);
        List<Type> typeList = new ArrayList<Type>();
        for (TypeExp texp : typeExpList)
            typeList.add(texp.toType());
        return typeList;
    }

    public TypeBindings declaredTypeBindings() {
	List<Type> formalTypeList = formalTypeList();
	return new TypeBindings(varList, formalTypeList);
    }

    public String toString() {
        String s = "";
        String sep = "";
        Iterator<Token> varIterator = varList.iterator();
        Iterator<TypeExp> typeIterator = typeExpList.iterator();
        while (varIterator.hasNext()) {
            s += sep +
                 varIterator.next().toString() +
                 ":" +
                 typeIterator.next().toType();
            sep = ",";
        }
        return s;
    }

}
