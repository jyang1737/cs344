import java.util.*;
//Empty:import//

// <arglist>:Empty ::= 
public class Empty extends Arglist {



    public Empty() {

    }

    public static Empty parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<arglist>:Empty", scn$.lno);
        return new Empty();
    }

//Empty//

}
