package uj.jwzp.w2.e3;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateRangeProperty implements Property {
    private ZonedDateTime from;
    private ZonedDateTime to;

    public DateRangeProperty(ZonedDateTime val1, ZonedDateTime val2) {
        from = val1;
        to = val2;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(from).append(':').append(to).toString();
    }

    public ZonedDateTime random() {
        return from.plusNanos(Math.round(Math.random() * from.until(to, ChronoUnit.NANOS)));
    }
}
