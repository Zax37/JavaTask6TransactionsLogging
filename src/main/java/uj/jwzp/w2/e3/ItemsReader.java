package uj.jwzp.w2.e3;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ItemsReader {
    public static List<Item> getFromCSV(InputStream is) throws IOException, NoSuchElementException {
        List<Item> ret = new ArrayList<>();
        Scanner scanner = new Scanner(is).useDelimiter("[,\n]");
        final String firstColumnHeader = scanner.next();
        final String secondColumnHeader = scanner.next();

        if(!firstColumnHeader.equals("name") || !secondColumnHeader.equals("price")) {
            throw new IOException("Unknown file format.");
        }
        while (scanner.hasNext()) {
            String name = scanner.next();
            name = name.substring(1, name.length()-1);
            Double price = Double.parseDouble(scanner.next());
            ret.add(new Item(name, BigDecimal.valueOf(price)));
        }
        return ret;
    }

    //Util class, not for instantiation
    private ItemsReader() {}
}
