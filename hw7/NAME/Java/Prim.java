import java.util.*;
//Prim:import//

public abstract class Prim {

    public static Prim parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case SUBOP:
            return SubPrim.parse(scn$,trace$);
        case SUB1OP:
            return Sub1Prim.parse(scn$,trace$);
        case ADD1OP:
            return Add1Prim.parse(scn$,trace$);
        case ADDOP:
            return AddPrim.parse(scn$,trace$);
        case MULOP:
            return MulPrim.parse(scn$,trace$);
        case DIVOP:
            return DivPrim.parse(scn$,trace$);
        case ZEROP:
            return ZeropPrim.parse(scn$,trace$);
        default:
            throw new RuntimeException("Prim cannot begin with " + v$);
        }
    }

    // apply the primitive to the passed values
    public abstract Val apply(Val [] va);

}
