package uj.jwzp.w2.e3;

public class IntRangeProperty implements Property {
    private int from;
    private int to;

    public IntRangeProperty(int val1, int val2) {
        from = val1;
        to = val2;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(from).append(':').append(to).toString();
    }

    public int random() {
        return from + (int)Math.round(Math.random() * (to - from));
    }
}
