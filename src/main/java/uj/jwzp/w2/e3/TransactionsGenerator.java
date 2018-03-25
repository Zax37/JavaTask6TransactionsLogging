package uj.jwzp.w2.e3;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class TransactionsGenerator {
    private TransactionsGenerator(){ } //Main class, no constructor needed

    public static HashMap<String, Property> getDefaultSettings() {
        HashMap<String, Property> settings = new HashMap<>();
        settings.put("customerIds", new IntRangeProperty(1, 20));
        ZonedDateTime today = ZonedDateTime.now();
        settings.put("dateRange", new DateRangeProperty(
                today.with(LocalTime.MIN),
                today.with(LocalTime.MAX))
        );
        settings.put("itemsFile", new StringProperty("items.csv"));
        settings.put("itemsCount", new IntRangeProperty(1, 5));
        settings.put("itemsQuantity", new IntRangeProperty(1, 5));
        settings.put("eventsCount", new IntProperty(100));
        settings.put("outDir", new StringProperty("./"));
        return settings;
    }

    public static void readSettingsFromArgs
    (
        HashMap<String, Property> settings,
        String[] args
    ) throws UnexpectedException  {
        String key = null;

        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                if (key != null) {
                    throw new UnexpectedException("Value not set for key: "+key);
                }
                key = arg.substring(1);
                if (!settings.containsKey(key)) {
                    throw new UnexpectedException("Unknown key: "+key);
                }
            } else {
                if (settings.get(key) instanceof IntProperty) {
                    settings.put(key, new IntProperty(Integer.parseInt(arg)));
                } else if (settings.get(key) instanceof IntRangeProperty) {
                    String[] parts = arg.split(":");
                    if (parts.length != 2) {
                        throw new UnexpectedException("Couldn't parse this IntRange: "+arg);
                    }
                    settings.put(key, new IntRangeProperty(
                            Integer.parseInt(parts[0]),
                            Integer.parseInt(parts[1])
                    ));
                } else if (settings.get(key) instanceof DateRangeProperty) {
                    if (arg.length() != 28*2+1) { //two dates separated by :
                        throw new UnexpectedException("Couldn't parse this DateRange: "+arg);
                    }
                    settings.put(key, new DateRangeProperty(
                            ZonedDateTime.parse(arg.substring(0, 28), Transaction.DATE_TIME_FORMATTER),
                            ZonedDateTime.parse(arg.substring(29, 28*2+1), Transaction.DATE_TIME_FORMATTER)
                    ));
                } else if (settings.get(key) instanceof StringProperty) {
                    settings.put(key, new StringProperty(arg));
                } else {
                    throw new UnexpectedException("Unknown property type for key: "+key);
                }
                key = null;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, Property> settings = getDefaultSettings();

        readSettingsFromArgs(settings, args);

        IntRangeProperty customerIds = (IntRangeProperty) settings.get("customerIds");
        DateRangeProperty dateRange = (DateRangeProperty) settings.get("dateRange");
        IntRangeProperty itemsCount = (IntRangeProperty) settings.get("itemsCount");
        IntRangeProperty itemsQuantity = (IntRangeProperty) settings.get("itemsQuantity");
        IntProperty eventsCount = (IntProperty) settings.get("eventsCount");
        String itemsInputFileLocation = settings.get("itemsFile").toString();
        String outputFilesLocation = settings.get("outDir").toString();

        try (FileInputStream input = new FileInputStream(itemsInputFileLocation)) {
            List<Item> availableItems = ItemsReader.getFromCSV(input);
            for (int i = 1; i <= eventsCount.getValue(); i++) {
                Collections.shuffle(availableItems);
                int count = itemsCount.random();
                LinkedHashMap<Item, Integer> orderedItems = new LinkedHashMap<>();
                for (int j = 1; j <= count; j++) {
                    orderedItems.put(availableItems.get(j), itemsQuantity.random());
                }
                FileOutputStream output =
                             new FileOutputStream(Paths.get(outputFilesLocation, i+".json").toString());
                output.write(
                        new Transaction.TransactionBuilder()
                                .id(i)
                                .timestamp(dateRange.random())
                                .customer_id(customerIds.random())
                                .itemsOrdered(orderedItems)
                                .build().toString().getBytes()
                );
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
