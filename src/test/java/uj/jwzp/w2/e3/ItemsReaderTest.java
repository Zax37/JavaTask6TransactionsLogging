package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public class ItemsReaderTest {
    private final static String INVALID_CSV_DATA1 = "invalid,price";
    private final static String INVALID_CSV_DATA2 = "name,invalid";
    private final static String CORRECT_CSV_DATA = "name,price\n\"testName\",1.37";
    private final static InputStream CORRECT_CSV_INPUTSTREAM = new ByteArrayInputStream(CORRECT_CSV_DATA.getBytes());
    private final static InputStream INVALID_CSV_INPUTSTREAM1 = new ByteArrayInputStream(INVALID_CSV_DATA1.getBytes());
    private final static InputStream INVALID_CSV_INPUTSTREAM2 = new ByteArrayInputStream(INVALID_CSV_DATA2.getBytes());
    private final static String TEST_PRODUCT1_NAME = "testName";
    private final static BigDecimal TEST_PRODUCT1_PRICE = BigDecimal.valueOf(1.37);
    private final static int TEST_PRODUCTS_COUNT = 1;

    @Test
    public void shouldReadCorrectCSV() {
        try {
            List<Item> uut = ItemsReader.getFromCSV(CORRECT_CSV_INPUTSTREAM);
            Assert.assertEquals(TEST_PRODUCTS_COUNT, uut.size());

            Item product1 = uut.get(0);

            Assert.assertEquals(TEST_PRODUCT1_NAME, product1.getName());
            Assert.assertEquals(TEST_PRODUCT1_PRICE, product1.getPrice());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionWhenCSVIncorrect1() throws IOException {
        ItemsReader.getFromCSV(INVALID_CSV_INPUTSTREAM1);
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionWhenCSVIncorrect2() throws IOException {
        ItemsReader.getFromCSV(INVALID_CSV_INPUTSTREAM2);
    }
}
