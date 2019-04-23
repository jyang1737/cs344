import java.util.*;

public class StdClass extends ClassVal {

    public ClassVal superClass;
    public Bindings staticBindings;
    public Fields fields;
    public Methods methods;
    public Env staticEnv;

    public StdClass(
            ClassVal superClass, // evaluated by ClassExp
            Statics statics,
            Fields fields,
            Methods methods) {
        this.superClass = superClass;
        this.fields = fields;
        this.methods = methods;
	// my static environment starts with the superclass environment
        staticEnv = superClass.env();
        // the staticBindings field is used to create instances of this class
        staticBindings = new Bindings();
        staticEnv = staticEnv.extendEnvRef(staticBindings);
        // initially create bindings for these static symbols ...
        staticBindings.add("myclass", new ValRef(this));
        staticBindings.add("superclass", new ValRef(superClass));
        // The static RHS expressions are evaluated in the modified
        // staticEnv that includes the bindings for myclass, superclass.
        // New static bindings are added as they are created,
        // as in top-level defines
        statics.addStaticBindings(staticBindings, staticEnv);
    }

    public Env env() {
        return staticEnv;
    }

    public ObjectVal makeObject(Ref objRef) {
        // System.err.println("... in makeObject ...");
        // create the parent object first (recursively)
        ObjectVal parent = superClass.makeObject(objRef);

        // this object's environment extends the parent object's environment
        Env e = parent.objectEnv;

        // add this class's static bindings (including those for myclass, etc)
        e = e.extendEnvRef(staticBindings);

        // the fields come next
        // three fields are pre-defined: 'super', 'self', and 'this'
        // bind 'super' to the parent object
        // bind 'self' to the base object (an instance of a derived class?)
        // bind 'this' to this object
        // 'self' is unnecessary here, except that it speeds up lookups
        Bindings fieldBindings = new Bindings();
        e = e.extendEnvRef(fieldBindings);
        // bind 'super' field to the parent object
        fieldBindings.add("super", new ValRef(parent)); // parent object
        // bind all of this object's instance fields to nil
        for (Token t : fields.varList) {
              String s = t.toString();
              fieldBindings.add(s, new ValRef(Val.nil));
        }


        // bind all this object's methods as in letrec
        // don't add any bindings if there are no method declarations
        if (methods.varList.size() > 0) {
            LetrecDecls methodDecls =
                new LetrecDecls(methods.varList, methods.procList);
            e = methodDecls.addBindings(e);
        }
        ObjectVal objectVal = new ObjectVal(e);
        // bind 'self' field to the base object being created
        // (to speed up lookups)
        fieldBindings.add("self", objRef); // deep
        // bind 'this' field to this object environment
        fieldBindings.add("this", new ValRef(objectVal)); // shallow

        // create the object and return it
        // System.err.println("...exiting makeObject...");
        return objectVal;
    }

    public String toString() {
        return "class";
    }

}