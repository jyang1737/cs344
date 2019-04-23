import java.util.*;

public abstract class Val {

    public static Val [] toArray(List<Val> valList) {
        int n = valList.size();
        return valList.toArray(new Val[n]);
    }

    public Val apply(List<Ref> refList) {
        throw new RuntimeException("cannot apply " + this);
    }

    public boolean isTrue() {
        return true; // everything is true except for an IntVal of zero
    }

    public IntVal intVal() {
        throw new RuntimeException(this + ": not an Int");
    }

    public ProcVal procVal() {
        throw new RuntimeException(this + ": not a Proc");
    }
}