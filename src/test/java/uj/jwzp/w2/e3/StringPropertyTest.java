package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Test;

public class StringPropertyTest {
    private final static String TEST_STRING = "ThisIsATestString";

    @Test
    public void shouldReturnSameAsInput() {
        StringProperty uut = new StringProperty(TEST_STRING);

        Assert.assertEquals(TEST_STRING, uut.toString());
    }
}
