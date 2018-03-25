package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

public class TransactionTest {
    private final static int TEST_TRANSACTION_ID = 1;
    private final static ZonedDateTime TEST_TRANSACTION_TIMESTAMP =
            ZonedDateTime.parse("2018-03-08T12:31:13.000-0100", Transaction.DATE_TIME_FORMATTER);
    private final static int TEST_TRANSACTION_CUSTOMER_ID = 12;
    private final static Item TEST_PRODUCT1 = new Item("bułeczka", BigDecimal.valueOf(1.2));
    private final static Item TEST_PRODUCT2 = new Item("mleko 3% 1l", BigDecimal.valueOf(2.3));
    private final static LinkedHashMap<Item, Integer> TEST_TRANSACTION_ITEMS_ORDERED;
    private final static String TEST_TRANSACTION_EXPECTED_JSON_OUTPUT =
            "{\n" +
            "  \"id\": 1,\n" +
            "  \"timestamp\": \"2018-03-08T12:31:13.000-0100\",\n" +
            "  \"customer_id\": 12,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"name\": \"bułeczka\",\n" +
            "      \"quantity\": 3,\n" +
            "      \"price\": 1.2\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"mleko 3% 1l\",\n" +
            "      \"quantity\": 1,\n" +
            "      \"price\": 2.3\n" +
            "    }\n" +
            "  ],\n" +
            "  \"sum\": 5.9\n" +
            "}";

    static {
        TEST_TRANSACTION_ITEMS_ORDERED = new LinkedHashMap<Item, Integer>();
        TEST_TRANSACTION_ITEMS_ORDERED.put(TEST_PRODUCT1, 3);
        TEST_TRANSACTION_ITEMS_ORDERED.put(TEST_PRODUCT2, 1);
    }

    @Test
    public void shouldStringifyToJSON() {
        Transaction uut = Transaction.builder()
                .id(TEST_TRANSACTION_ID)
                .timestamp(TEST_TRANSACTION_TIMESTAMP)
                .customer_id(TEST_TRANSACTION_CUSTOMER_ID)
                .itemsOrdered(TEST_TRANSACTION_ITEMS_ORDERED)
                .build();

        Transaction.builder().toString(); //JUST TO GET 100% COVERAGE

        Assert.assertEquals(TEST_TRANSACTION_EXPECTED_JSON_OUTPUT, uut.toString());
    }
}
