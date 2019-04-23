import java.util.*;
//Prim:import//

public abstract class Prim {

    public static Prim parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Val v$ = t$.val;
        switch(v$) {
        case SUB1OP:
            return Sub1Prim.parse(scn$,trace$);
        case NOTOP:
            return NotPrim.parse(scn$,trace$);
        case ANDOP:
            return AndPrim.parse(scn$,trace$);
        case DIVOP:
            return DivPrim.parse(scn$,trace$);
        case SUBOP:
            return SubPrim.parse(scn$,trace$);
        case LTP:
            return LTPrim.parse(scn$,trace$);
        case GTP:
            return GTPrim.parse(scn$,trace$);
        case GEP:
            return GEPrim.parse(scn$,trace$);
        case ADD1OP:
            return Add1Prim.parse(scn$,trace$);
        case OROP:
            return OrPrim.parse(scn$,trace$);
        case MULOP:
            return MulPrim.parse(scn$,trace$);
        case EQP:
            return EQPrim.parse(scn$,trace$);
        case LEP:
            return LEPrim.parse(scn$,trace$);
        case NEP:
            return NEPrim.parse(scn$,trace$);
        case ZEROP:
            return ZeropPrim.parse(scn$,trace$);
        case ADDOP:
            return AddPrim.parse(scn$,trace$);
        default:
            throw new RuntimeException("Prim cannot begin with " + v$);
        }
    }

    // apply the primitive to the passed values
    public abstract Val apply(Val [] va);
    public abstract ProcType definedType();

}
