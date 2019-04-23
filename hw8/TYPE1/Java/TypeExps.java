import java.util.*;
//TypeExps:import//

// <typeExps> **= <typeExp> +COMMA
public class TypeExps {

    public List<TypeExp> typeExpList;

    public TypeExps(List<TypeExp> typeExpList) {
        this.typeExpList = typeExpList;
    }

    public static TypeExps parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<typeExps>", scn$.lno);
        List<TypeExp> typeExpList = new ArrayList<TypeExp>();
        // first trip through the parse
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case INT:
        case BOOL:
        case LBRACK:
            while(true) {
                typeExpList.add(TypeExp.parse(scn$, trace$));
                t$ = scn$.cur();
                v$ = t$.val;
                if (v$ != Token.Val.COMMA)
                    break; // not a separator, so we're done
                scn$.match(v$, trace$);
            }
        } // end of switch
        return new TypeExps(typeExpList);

    }

//TypeExps//

}
