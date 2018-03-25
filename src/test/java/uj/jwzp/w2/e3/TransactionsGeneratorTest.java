package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.UnexpectedException;

public class TransactionsGeneratorTest {
    @Test
    public void shouldGetDefaultOptions() {
        String[] args = { };
        try {
            TransactionsGenerator.main(args);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void shouldChangeOneParameter() {
        String[] args = { "-eventsCount", "1" };
        try {
            TransactionsGenerator.main(args);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void shouldChangeAllParameters() {
        String[] args = { "-customerIds", "1:1", "-dateRange",
                "2018-03-08T12:31:13.000-0100:2018-03-08T12:31:13.000-0100",
                "-itemsFile", "items.csv", "-itemsCount", "1:1", "-itemsQuantity", "1:1",
                "-eventsCount", "1", "-outDir", "./"};
        try {
            TransactionsGenerator.main(args);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = UnexpectedException.class)
    public void shouldFailWhenKeyUknown() throws IOException {
        String[] args = { "-anything", "wrong" };
        TransactionsGenerator.main(args);
    }

    @Test(expected = UnexpectedException.class)
    public void shouldFailWhenNoValueForKey() throws IOException {
        String[] args = { "-dateRange", "-secondKey" };
        TransactionsGenerator.main(args);
    }

    @Test(expected = UnexpectedException.class)
    public void shouldFailWhenNoKeyForValue() throws IOException {
        String[] args = { "value" };
        TransactionsGenerator.main(args);
    }

    @Test(expected = UnexpectedException.class)
    public void shouldFailWhenIntRangeIncorrect() throws IOException {
        String[] args = { "-customerIds", "1,2,3" };
        TransactionsGenerator.main(args);
    }

    @Test(expected = UnexpectedException.class)
    public void shouldFailWhenDateRangeIncorrect() throws IOException {
        String[] args = { "-dateRange", "2018-03-08T12:31:13.000-0100" };
        TransactionsGenerator.main(args);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldFailWhenFileNotFound() throws IOException {
        String[] args = { "-itemsFile", "notExisting.csv" };
        try {
            TransactionsGenerator.main(args);
        } catch (UnexpectedException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
