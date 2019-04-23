import java.util.*;
//ProcTypeExp:import//

// <typeExp>:ProcTypeExp ::= LBRACK <typeExps> RARROW <typeExp> RBRACK
public class ProcTypeExp extends TypeExp {

    public TypeExps typeExps;
    public TypeExp typeExp;

    public ProcTypeExp(TypeExps typeExps, TypeExp typeExp) {
        this.typeExps = typeExps;
        this.typeExp = typeExp;
    }

    public static ProcTypeExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<typeExp>:ProcTypeExp", scn$.lno);
        scn$.match(Token.Val.LBRACK, trace$);
        TypeExps typeExps = TypeExps.parse(scn$, trace$);
        scn$.match(Token.Val.RARROW, trace$);
        TypeExp typeExp = TypeExp.parse(scn$, trace$);
        scn$.match(Token.Val.RBRACK, trace$);
        return new ProcTypeExp(typeExps, typeExp);
    }

    public Type toType() {
        List<Type> paramTypeList = new ArrayList<Type>();
        for (TypeExp te : typeExps.typeExpList)
            paramTypeList.add(te.toType());
        Type returnType = typeExp.toType();
        return new ProcType(paramTypeList, returnType);
    }

}
