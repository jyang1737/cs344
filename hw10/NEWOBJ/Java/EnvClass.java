import java.util.*;

public class EnvClass extends ClassVal {

    public Env staticEnv;

    public EnvClass(Env env) {
        // the static environment of this class extends the current environment
        Bindings staticBindings = new Bindings();
        staticEnv = env.extendEnvRef(staticBindings);
        // create bindings for these static symbols ...
        staticBindings.add("superclass", new ValRef(Val.nil));
        staticBindings.add("myclass", new ValRef(this));
    }

    public Env env() {
        return staticEnv;
    }

    // Observe that objects created with 'new ...' always end up
    // extending the static environment of this class
    // The env parameter is the environment in which the object is created
    public ObjectVal makeObject(Ref objRef) {
        // System.err.println("...makeObject (in EnvClass)...");
        // start with the static environment of this class
        Env e = staticEnv;
        // add the field binding 'self' to refer to the base object (deep)
        // and 'this' to this object (shallow)
        Bindings fieldBindings = new Bindings();
        e = e.extendEnvRef(fieldBindings);
        ObjectVal objectVal = new ObjectVal(e);
        fieldBindings.add("self", objRef); // deep
        fieldBindings.add("this", new ValRef(objectVal)); // shallow
        return objectVal;
    }

    public String toString() {
        // return "class:" + staticEnv.getDepth();
        return "class";
    }

}