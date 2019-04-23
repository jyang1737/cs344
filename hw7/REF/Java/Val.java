
/**
 * An object representing a run-time value in a program.
 * This is just a &quot;marker interface&quot;. As such
 * its only use is to prevent a programmer from putting
 * an inappropriate object into a place where some kind
 * of run-time value belongs.
 */
public interface Val {
    public default boolean isTrue() { return true; }
}
