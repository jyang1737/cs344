import java.util.*;
//Program:import//

public abstract class Program {

    public Program() { } // dummy constructor

    public static Program parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case DIVOP:
        case IF:
        case LIT:
        case LET:
        case VAR:
        case SUBOP:
        case SUB1OP:
        case ZEROP:
        case MULOP:
        case ADD1OP:
        case DOT:
        case SET:
        case LETREC:
        case LBRACE:
        case ADDOP:
        case PROC:
            return Eval.parse(scn$,trace$);
        case DEFINE:
            return Define.parse(scn$,trace$);
        default:
            throw new RuntimeException("Program cannot begin with " + v$);
        }
    }

    // The program's global environment
    protected static Env initEnv = Env.NULL.extendEnvRef(new Bindings());

    /**
     * The PLCC-generated code includes main programs that call toString()
     * on the root of the abstract syntax tree. In this design of the
     * tree nodes, we immediately invoke the abstract method execute()
     * to tell the tree to evaluate itself.
     */
    @Override
    public String toString() {
        return execute();
    }

    /**
     * Evaluate the entire generated abstract syntax tree.
     * (We currently assume that the Program node is always the tree's root.)
     */
    public abstract String execute();

}
