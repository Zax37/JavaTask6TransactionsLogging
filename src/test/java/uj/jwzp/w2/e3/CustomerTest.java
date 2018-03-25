package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    final static int TEST_CUSTOMER_ID = 1;
    final static String TEST_CUSTOMER_NAME = "Customer";
    final static String TEST_CUSTOMER_ADDRESS = "Kraków, \u0141ojasiewicza";

    @Test
    public void shouldReturnSameAsInput() {
        Customer uut = new Customer(TEST_CUSTOMER_ID, TEST_CUSTOMER_NAME, TEST_CUSTOMER_ADDRESS);

        Assert.assertEquals(TEST_CUSTOMER_ID, uut.getId());
        Assert.assertEquals(TEST_CUSTOMER_NAME, uut.getName());
        Assert.assertEquals(TEST_CUSTOMER_ADDRESS, uut.getAddress());
    }
}
