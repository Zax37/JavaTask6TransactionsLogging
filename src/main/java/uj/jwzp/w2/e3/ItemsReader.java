package uj.jwzp.w2.e3;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ItemsReader {
    private static final String[] expectedColumns = { "name", "price" };
    public static List<Item> getFromCSV(BufferedReader reader) throws IOException, NoSuchElementException {
        List<Item> ret = new ArrayList<>();
        String headerLine = reader.readLine();
        String[] columns = headerLine.split(",");

        if (columns.length != expectedColumns.length) {
            throw new IOException("Unknown file format.");
        }

        for (int i = 0; i < expectedColumns.length; i++) {
            if (!columns[i].equals(expectedColumns[i])) {
                throw new IOException("Unknown file format.");
            }
        }

        String line;
        while ((line = reader.readLine()) != null) {
            columns = line.split(",");

            if(columns.length != expectedColumns.length) {
                throw new IOException("Unknown file format.");
            }

            String name = columns[0];
            name = name.substring(1, name.length()-1);
            Double price = Double.parseDouble(columns[1]);
            ret.add(new Item(name, BigDecimal.valueOf(price)));
        }
        return ret;
    }

    //Util class, not for instantiation
    private ItemsReader() {}
}
