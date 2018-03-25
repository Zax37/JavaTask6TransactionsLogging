package uj.jwzp.w2.e3;

public class StringProperty implements Property {
    String value;

    public StringProperty (String val) {
        value = val;
    }

    @Override
    public String toString() {
        return value;
    }
}
