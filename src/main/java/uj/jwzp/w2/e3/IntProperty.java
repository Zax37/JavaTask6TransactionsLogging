package uj.jwzp.w2.e3;

public class IntProperty implements Property {
    private int value;

    public IntProperty(int val) {
        value = val;
    }

    int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
