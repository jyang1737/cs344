
/**
 * Binding of a single identifier to its value.
 * They go into a collection of bindings called Bindings.
 *
 * @see Bindings
 */
public class Binding {

    /**
     * The field representing the name of the variable
     */
    public final String id;

    /**
     * The field representing the value of the variable
     */
    public final Val val;

    /**
     * Construct a Binding and initialize its fields.
     */
    public Binding( String id, Val val ) {
        this.id = id;
        this.val = val;
    }

    /**
     * Return a representation of a binding that is a tuple:
     * <br><code>(<i>id</i>,<i>value</i>)</code>
     */
    @Override
    public String toString() {
        return "(" + id + ',' + val + ')';
    }
}
