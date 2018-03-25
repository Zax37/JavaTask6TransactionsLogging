package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Test;

public class IntPropertyTest {
    private final static int TEST_INTEGER = 33;
    private final static String TEST_STRING = "33";

    @Test
    public void shouldReturnSameAsInput() {
        IntProperty uut = new IntProperty(TEST_INTEGER);

        Assert.assertEquals(TEST_INTEGER, uut.getValue());
        Assert.assertEquals(TEST_STRING, uut.toString());
    }
}
