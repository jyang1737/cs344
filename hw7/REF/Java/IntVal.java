
/**
 * An object representing an integer run-time value in a program.
 */
public class IntVal implements Val {

    /** The integer within */
    public final int v;

    /** Construct a new value with the given integer. */
    public IntVal( int v ) {
        this.v = v;
    }

    /**
     * Return a representation of this value as a decimal string.
     */
    @Override
    public String toString() {
        return Integer.toString( v );
    }

    /**
     * Return false if this Val's integer is 0; true otherwise.
     * Useful when an integer is used as a test in a conditional expression.
     */
    public boolean isTrue() {
        return v != 0;
    }
}
