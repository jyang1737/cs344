import java.util.*;
//ArgChain:import//

// <argChain> **= <SYMBOL> <basic>
public class ArgChain {

    public List<Token> symbolList;
    public List<Basic> basicList;

    public ArgChain(List<Token> symbolList, List<Basic> basicList) {
        this.symbolList = symbolList;
        this.basicList = basicList;
    }

    public static ArgChain parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<argChain>", scn$.lno);
        List<Token> symbolList = new ArrayList<Token>();
        List<Basic> basicList = new ArrayList<Basic>();
        while (true) {
            Token t$ = scn$.cur();
            Token.Val v$ = t$.val;
            switch(v$) {
            case SYMBOL:
                symbolList.add(scn$.match(Token.Val.SYMBOL, trace$));
                basicList.add(Basic.parse(scn$, trace$));
                continue;
            default:
                return new ArgChain(symbolList, basicList);
            }
        }

    }

   public String toString() {
       String ret = "";
       if (symbolList.size() > 0) {
           ret += ".";
       }
       for (Token temp : symbolList) {
           ret += temp.str;
       }
       if (basicList.size() > 0) {
           ret += "(";
           ret += basicList.get(0).toString();
           for (int i = 1; i < basicList.size(); i++) {
               ret += ",";
               ret += basicList.get(i).toString();
           }
           ret += ")";
       }
       return ret;
   }

}
