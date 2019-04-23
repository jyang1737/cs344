
/**
 * A standard environment containing a Bindings object (set of bindings)
 * and possibly a chain to an outer-scope environment
 */
public class EnvNode extends Env {

    /** List of local bindings */
    private Bindings bindings;

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
    public EnvNode extendEnv(Bindings bindings) {
        return new EnvNode(bindings, this);
    }

    /**
     * Add a new Binding to this environment node. It is put at the
     * end of the binding list, meaning it will be consulted only
     * if no match for a variable is found earlier in the list.
     */
    public void add(Binding b) {
	bindings.add(b);
    }

    /**
     * Add a new Binding to this environment node. It is put at the
     * start of the binding list, meaning that if there are multiple
     * Bindings for the same identifier, this one will be found.
     */
    public void addFirst(Binding b) {
	bindings.addFirst(b);
    }

    /**
     * Find the reference associated with the given symbol either in
     * this environment, or by recursively searching in the enclosing
     * scope.
     * @throws RuntimeException of there is no such binding
     */
    @Override
    public Ref applyEnvRef(String sym) {
        // Look first in the local bindings.
        for (Binding b : bindings.bindings()) {
            if (sym.equals(b.id))
                return b.ref;
        }
        // Not found in the local bindings,
        // so look in the next (enclosing) environment.
        return env.applyEnvRef(sym);
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

    /**
     * Replace bindings with a whole new one. (special use: letrec)
     */
    public void replaceBindings( Bindings newBindings ) {
        bindings = newBindings;
    }
}

