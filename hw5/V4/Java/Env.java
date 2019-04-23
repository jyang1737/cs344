
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
     * Return the value currently bound to sym.
     * @throws RuntimeException of there is no such binding
     */
    public abstract Val applyEnv(String sym);

    /**
     * Extend the current environment by adding multiple bindings.
     */
    public Env extendEnv(Bindings bindings) {
        return new EnvNode(bindings, this);
    }

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
     * Internal method used to create environment representations
     * without the surrounding braces, so that the chains look good
     */
    protected abstract String envListing();

    private static class EnvNull extends Env {
        /**
         * Return the value currently bound to sym. This always fails.
         * @throws RuntimeExcpetion of there is no such binding
         */
        @Override
        public Val applyEnv(String sym) {
            throw new RuntimeException("no binding for "+sym);
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
        Env env1 = env0.extendEnv(
            new Bindings( Arrays.asList(
                new Binding("a", new IntVal(1)),
                new Binding("b", new IntVal(2)),
                new Binding("c", new IntVal(3)))));
        List<String> i2 = Arrays.asList("a", "p");
        List<Val> v2 = Arrays.asList(new IntVal(4), new IntVal(5));
        Env env2 = env1.extendEnv(new Bindings(i2, v2));
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

