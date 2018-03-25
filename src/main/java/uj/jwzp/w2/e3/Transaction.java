package uj.jwzp.w2.e3;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Builder
public class Transaction {
    private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    private int id;
    private ZonedDateTime timestamp;
    private int customer_id;
    private LinkedHashMap<Item, Integer> itemsOrdered;

    public BigDecimal getSum() {
        BigDecimal ret = BigDecimal.ZERO;
        for (Map.Entry<Item, Integer> entry : itemsOrdered.entrySet()) {
            ret = ret.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\n  \"id\": ");
        sb.append(id);
        sb.append(",\n  \"timestamp\": \"");
        sb.append(timestamp.format(DATE_TIME_FORMATTER));
        sb.append("\",\n  \"customer_id\": ");
        sb.append(customer_id);
        sb.append(",\n  \"items\": [");
        boolean first = true;
        for (Map.Entry<Item, Integer> entry : itemsOrdered.entrySet()) {
            sb.append(first?"\n    {":",\n    {");
            sb.append("\n      \"name\": \"");
            sb.append(entry.getKey().getName());
            sb.append("\",\n      \"quantity\": ");
            sb.append(entry.getValue());
            sb.append(",\n      \"price\": ");
            sb.append(entry.getKey().getPrice());
            sb.append("\n    }");
            first = false;
        }
        sb.append("\n  ],\n  \"sum\": ");
        sb.append(getSum());
        sb.append("\n}");
        return sb.toString();
    }
}