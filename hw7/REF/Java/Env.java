
import java.util.Arrays;
import java.util.List;

/**
 * A wrapper for a set of bindings that represents a single scope
 * in an executing program. As inner scopes are entered, Env objects
 * are chained together. The chaining functionality is implemented
 * here. Concrete subclasses implement the binding functionality.
 * Note that we assume that the symbols in the individual bindings
 * are Strings.
 */
public abstract class Env {

    /**
     * An environment identifier counter
     * @see Env#id
     */
    private static int idCounter = 0;

    /**
     * The environment instance's ID.
     * This is here because it is too costly in terms of display space
     * to print out entire environments every time a closure is
     * displayed via toString.
     *
     * @see Env#simpleString
     */
    public final int id;

    /**
     * Create an empty environment with a new id.
     */
    public Env() {
        id = idCounter++;
    }

    /**
     * Return the value referenced by the reference currently bound to sym.
     * @throws RuntimeException if there is no such binding
     */
    public Val applyEnv(String sym) {
        return applyEnvRef( sym ).deRef();
    }

    /**
     * Return the reference currently bound to sym.
     * @throws RuntimeException if there is no such binding
     */
    public abstract Ref applyEnvRef( String sym );

    /**
     * Extend the current environment by adding multiple bindings.
     */
    public EnvNode extendEnvRef(Bindings bindings) {
        return new EnvNode(bindings, this);
    }

    /**
     * Add a new Binding to this environment node. It is put at the
     * end of the binding list, meaning it will be consulted only
     * if no match for a variable is found earlier in the list.
     */
    public abstract void add(Binding b);

    /**
     * Add a new Binding to this environment node. It is put at the
     * start of the binding list, meaning that if there are multiple
     * Bindings for the same identifier, this one will be found.
     */
    public abstract void addFirst(Binding b);

    /**
     * Return a representation of an environment that consists
     * of the bindings in this environment, plus, recursively,
     * the representation of the environment to which it is
     * chained, if any. The entire string is bounded with braces.
     */
    @Override
    public String toString() {
        return "{" + envListing() + "}";
    }

    /**
     * Represent this environment as a short string that just shows
     * the Env's unique id.
     */
    public String simpleString() {
        return "{" + id + "}";
    }

    /**
     * Internal method used to create environment representations
     * without the surrounding braces, so that the chains look good
     */
    protected abstract String envListing();

    private static class EnvNull extends Env {
        /**
         * Return the value currently bound to sym. This always fails.
         * @throws RuntimeException of there is no such binding
         */
        @Override
        public Ref applyEnvRef(String sym) {
            throw new RuntimeException("no binding for "+sym);
        }

        /**
         * Add a new Binding to the end of the bindings list.
         * This always fails.
         * @throws RuntimeException of inability to add the binding
         */
        @Override
        public void add(Binding b) {
	    throw new RuntimeException("Cannot add binding to a null env.");
        }

        /**
         * Add a new Binding to the start of the bindings list.
         * This always fails.
         * @throws RuntimeException of inability to add the binding
         */
        @Override
        public void addFirst(Binding b) {
	    throw new RuntimeException("Cannot add binding to a null env.");
        }

        /**
         * Return an empty string.
         */
        @Override
        protected String envListing() {
            return "";
        }
    }

    /**
     * An initial (empty) environment.
     * This single instance has no state, so it is a singleton
     * that can be shared by everyone.
     */
    public final static Env NULL = new EnvNull();

    /**
     * Env package test program
     */
    public static void main(String [] args) {
        Env env0 = Env.NULL;
        List<String> i1 = Arrays.asList( "a", "b", "c" );
        List<Val> v1  = Arrays.asList(
                                new IntVal( 1 ),
                                new IntVal( 2 ),
                                new IntVal( 3 )
        );
        Env env1 = env0.extendEnvRef(
                            new Bindings( i1, Ref.valsToRefs( v1 ) ) );

        List<String> i2 = Arrays.asList("a", "p");
        List<Val> v2 = Arrays.asList(new IntVal(4), new IntVal(5));
        Env env2 = env1.extendEnvRef(new Bindings(i2, Ref.valsToRefs(v2)));
        try {
            System.out.println("env0:\n" + env0);
            System.out.println("env1:\n" + env1);
            System.out.println("env2:\n" + env2);
            System.out.print("a(env2) => ");
                System.out.println(env2.applyEnv("a"));
            System.out.print("a(env1) => ");
                System.out.println(env1.applyEnv("a"));
            System.out.print("p(env2) => ");
                System.out.println(env2.applyEnv("p"));
            System.out.print("p(env1) => ");
                System.out.println(env1.applyEnv("p"));
        }
        catch( Exception e ) {
            System.err.println( e );
        }
    }
}

