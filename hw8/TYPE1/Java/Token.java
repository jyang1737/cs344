import java.util.*;
import java.util.regex.*;

// Token class with match patterns (used with the built-in Scan class)
public class Token {
       
    public enum Val {
        WHITESPACE ("\\s+", true),
        COMMENT ("%.*", true),
        LIT ("\\d+"),
        LPAREN ("\\("),
        RPAREN ("\\)"),
        COMMA (","),
        ADDOP ("\\+"),
        SUBOP ("\\-"),
        MULOP ("\\*"),
        DIVOP ("/"),
        ADD1OP ("add1"),
        SUB1OP ("sub1"),
        ANDOP ("and"),
        OROP ("or"),
        NOTOP ("not"),
        LTP ("<\\?"),
        LEP ("<=\\?"),
        GTP (">\\?"),
        GEP (">=\\?"),
        EQP ("=\\?"),
        NEP ("<>\\?"),
        ZEROP ("zero\\?"),
        IF ("if"),
        THEN ("then"),
        ELSE ("else"),
        LET ("let"),
        LETREC ("letrec"),
        DEFINE ("define"),
        DECLARE ("declare"),
        IN ("in"),
        EQUALS ("="),
        PROC ("proc"),
        SET ("set"),
        DOT ("\\."),
        LBRACE ("\\{"),
        RBRACE ("\\}"),
        LBRACK ("\\["),
        RBRACK ("\\]"),
        COLON (":"),
        RARROW ("=>"),
        BOOL ("bool"),
        INT ("int"),
        TRUE ("true"),
        FALSE ("false"),
        SEMI (";"),
        VAR ("[A-Za-z][\\w?]*");

	public String pattern;
        public boolean skip;
	public Pattern cPattern; // compiled pattern

        // a token pattern (skip == false)
        Val(String pattern) {
            this(pattern, false);
	}

        Val(String pattern, boolean skip) {
	    this.pattern = pattern;
            this.skip = skip;
	    this.cPattern = Pattern.compile(pattern, Pattern.DOTALL);
	}
    }

    public Val val;          // token
    public String str;       // the token string matched
    public int lno;          // the line number where this token was found

    public Token() {
	val = null;
        str = null;
        lno = 0;
    }
    
    public Token(Val val, String str, int lno) {
        this.val = val;
        this.str = str;
	this.lno = lno;
    }

    public Token(Val val, String str) {
	this(val, str, 0);
    }

    public String toString() {
        return str;
    }

    public static void main(String [] args) {
	for (Val v : Val.values()) {
            String what;
            if (v.skip)
                what = "skip ";
            else
                what = "token ";
	    System.out.println(what + v + " '" + v.pattern + "'");
        }
    }

//Token//

}
