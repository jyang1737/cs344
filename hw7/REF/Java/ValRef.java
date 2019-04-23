
/**
 * A reference to another Val.
 * Consider it a memory location if you want.
 */
public class ValRef implements Ref {

    private Val val;

    /**
     * Set this reference to its initial value.
     */
    public ValRef( Val val ) {
        this.val = val;
    }

    /**
     * Return the value to which this ValRef object is referring.
     */
    public Val deRef() {
        return val;
    }

    /**
     * Change the value at the location that this ValRef object
     * represents.
     * @param v the new value
     * @return the new value, i.e., the argument
     */
    public Val setRef( Val v ) {
        val = v;
        return v;
    }
}
