import java.util.*;
//IfExp:import//

// <exp>:IfExp ::= IF <exp>testExp THEN <exp>trueExp ELSE <exp>falseExp
public class IfExp extends Exp {

    public Exp testExp;
    public Exp trueExp;
    public Exp falseExp;

    public IfExp(Exp testExp, Exp trueExp, Exp falseExp) {
        this.testExp = testExp;
        this.trueExp = trueExp;
        this.falseExp = falseExp;
    }

    public static IfExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:IfExp", scn$.lno);
        scn$.match(Token.Val.IF, trace$);
        Exp testExp = Exp.parse(scn$, trace$);
        scn$.match(Token.Val.THEN, trace$);
        Exp trueExp = Exp.parse(scn$, trace$);
        scn$.match(Token.Val.ELSE, trace$);
        Exp falseExp = Exp.parse(scn$, trace$);
        return new IfExp(testExp, trueExp, falseExp);
    }

    public Val eval(Env env) {
        Val testVal = testExp.eval(env);
        if (testVal.isTrue())
            return trueExp.eval(env);
        else
            return falseExp.eval(env);
    }

    public Type evalType(TypeEnv tenv) {
        Type testType = testExp.evalType(tenv);
        testType.checkBoolType(Type.boolType);
        Type trueExpType = trueExp.evalType(tenv);
        Type falseExpType = falseExp.evalType(tenv);
        Type.checkEquals(trueExpType, falseExpType);
        return trueExpType;
    }

    public String toString() {
        return "if " + testExp + " then " + trueExp + " else " +falseExp;
    }

}
