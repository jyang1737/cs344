import java.util.*;
//Word:import//

// <arg>:Word ::= <ID>
public class Word extends Arg {

    public Token id;

    public Word(Token id) {
        this.id = id;
    }

    public static Word parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<arg>:Word", scn$.lno);
        Token id = scn$.match(Token.Val.ID, trace$);
        return new Word(id);
    }

//Word//

}
