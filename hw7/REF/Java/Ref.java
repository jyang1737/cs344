import java.util.ArrayList;
import java.util.List;

/**
 * A reference to some other value. This class realizes indirection
 * and the notion of an <i>lvalue</i> that can be used on the left
 * hand side of a SET (reassignment) statement.
 */
public interface Ref {

    public static List< Ref > valsToRefs( List< Val > valList ) {
        List< Ref > refList = new ArrayList<>();
        for ( Val v : valList )
            refList.add( new ValRef( v ) );
        return refList;
    }

    /**
     * Return the value to which this Ref object is referring.
     */
    public abstract Val deRef();

    /**
     * Change the value at the location to which this Ref object
     * is referring.
     * @param v the new value
     * @return the new value, i.e., the argument
     */
    public abstract Val setRef( Val v );
}
