import java.util.*;

/** Singleton */
public class NilVal extends Val {

    private NilVal() {}

    public static final NilVal instance = new NilVal();

    public boolean isTrue() {
        return false;
    }

    public String toString() {
        return "nil";
    }

}