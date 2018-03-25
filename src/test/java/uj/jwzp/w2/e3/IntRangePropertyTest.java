package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Test;

public class IntRangePropertyTest {
    private final static int TEST_INTEGER_MIN = 1;
    private final static int TEST_INTEGER_MAX = 10;
    private final static String TEST_STRING = "1:10";

    @Test
    public void shouldReturnRandomInRange() {
        IntRangeProperty uut = new IntRangeProperty(TEST_INTEGER_MIN, TEST_INTEGER_MAX);

        int randomBetween = uut.random();

        Assert.assertTrue(randomBetween >= TEST_INTEGER_MIN);
        Assert.assertTrue(randomBetween <= TEST_INTEGER_MAX);
    }

    @Test
    public void shouldConvertToStringProperly() {
        IntRangeProperty uut = new IntRangeProperty(TEST_INTEGER_MIN, TEST_INTEGER_MAX);

        Assert.assertEquals(TEST_STRING, uut.toString());
    }
}
