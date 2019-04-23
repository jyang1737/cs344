
import java.util.List;
import java.util.stream.Collectors;

/**
 * An object representing a procedure (actually a closure) at run time.
 */
public class ProcVal implements Val {

    /** The list of parameter names */
    private Formals formals;

    /**
     * The expression that is evaluated when the procedure is called.
     * body refers to a node in the parse tree.
     */
    private Exp body;

    /**
     * The environment in which the body is evaluated.
     * (Thus the name <i>closure</i>.)
     */
    private Env env;

    /** Construct a new procedure with the given data. */
    public ProcVal( Formals formals, Exp body, Env env ) {
        this.formals = formals;
        this.body = body;
        this.env = env;
    }

    // private int nesting = 0;

    /**
     * Return a representation of this procedure using
     * comfortable syntax. This is difficult for recursive procedures
     * since their environments contain themselves. Therefore a
     * recursive procedure's ProcVal only shows its environment at
     * the first (outermost) level.
     */
    @Override
    public String toString() {
        // nesting += 1;
        // String envStr = ( nesting == 1 ) ? env.toString() : "{...}";
        String envStr = env.simpleString();
        String result =
            "CLOSURE<" +
                "λ" + formals +
                " { return " + body + " }" +
                ",env=" + envStr +
            '>';
        // nesting -= 1;
        return result;
    }


    /**
     * Apply (&quot;call&quot;) this function to a given list of arguments.
     * @param args the actual arguments (already evaluated)
     * @param e not used at this time
     */
    public Val apply( List< Ref > refList, Env e ) {
        List< String > varNameList =
            formals.varList.stream()
                            .map( Token::toString )
                            .collect( Collectors.toList() );
        Bindings bindings = new Bindings( varNameList, refList );
	Env newEnv = env.extendEnvRef( bindings );
        return body.eval( newEnv );
    }
}
