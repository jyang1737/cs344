
/**
 * A standard environment containing a Bindings object (set of bindings)
 * and possibly a chain to an outer-scope environment
 */
public class EnvNode extends Env {

    /** List of local bindings */
    private final Bindings bindings;

    /** Enclosing scope */
    public final Env env;

    /**
     * Create an environment, given a set of bindings and the
     * environment of the enclosing scope.
     */
    public EnvNode(Bindings bindings, Env env) {
        this.bindings = bindings;
        this.env = env;
    }

    /**
     * Create a new environment with new bindings and an enclosing
     * scope that is this environment.
     */
    public Env extendEnv(Bindings bindings) {
        return new EnvNode(bindings, this);
    }

    /**
     * Find the value associated with the given symbol either in
     * this environment, or by recursively searching in the enclosing
     * scope.
     * @throws RuntimeException of there is no such binding
     */
    public Val applyEnv(String sym) {
        // Look first in the local bindings.
        for (Binding b : bindings.bindings()) {
            if (sym.equals(b.id))
                return b.val;
        }
        // Not found in the local bindings,
        // so look in the next (enclosing) environment.
        return env.applyEnv(sym);
    }

    /**
     * Return the concatenation of this environment's Bindings object
     * followed by those to which this object is chained.
     */
    public String envListing() {
        String result = bindings.toString();
        result += env.envListing();
        return result;
    }
}

