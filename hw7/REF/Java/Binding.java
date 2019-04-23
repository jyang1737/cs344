
/**
 * Binding of a single identifier to a reference to its value.
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
     * The field representing a reference to the value of the variable
     */
    public final Ref ref;

    /**
     * Construct a Binding and initialize its fields.
     */
    public Binding( String id, Ref ref ) {
        this.id = id;
        this.ref = ref;
    }

    /**
     * Return a representation of a binding that is a tuple:
     * <br><code>(<i>id</i>,<i>value</i>)</code>
     */
    @Override
    public String toString() {
        return "(" + id + ',' + ref.deRef() + ')';
    }
}
