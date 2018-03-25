package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.time.ZonedDateTime;

public class DateRangePropertyTest {
    private final static ZonedDateTime NOW = ZonedDateTime.now();
    private final static ZonedDateTime TODAY_MORNING = NOW.with(LocalTime.MIN);
    private final static ZonedDateTime TODAY_EVENING = NOW.with(LocalTime.MAX);
    private final static String STRING_RANGE = TODAY_MORNING.toString() + ":" + TODAY_EVENING.toString();

    @Test
    public void shouldReturnRandomInRange() {
        DateRangeProperty uut = new DateRangeProperty(
                TODAY_MORNING,
                TODAY_EVENING
        );
        ZonedDateTime randomBetween = uut.random();
        Assert.assertTrue(randomBetween.compareTo(TODAY_MORNING) >= 0);
        Assert.assertTrue(randomBetween.compareTo(TODAY_EVENING) <= 0);
    }

    @Test
    public void shouldConvertToStringProperly() {
        DateRangeProperty uut = new DateRangeProperty(
                TODAY_MORNING,
                TODAY_EVENING
        );

        Assert.assertEquals(STRING_RANGE, uut.toString());
    }
}
