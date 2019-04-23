public class StrVal implements Val {
    private final String v;

    public StrVal(String v) {
        this.v = v.replaceAll("\"", "");
    }

    public boolean isTrue() {
        return v.equals("");
    }

    public String strVal() {
        return this.v;
    }

    public String toString() {
        return "\"" + this.v + "\"";
    }
}

