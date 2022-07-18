package com.stream;

import com.memorynotfound.integration.Price;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;

public class StreamTest {

    private static Logger logger = Logger.getLogger("StreamTest.class");
    private static final String COMMA_DELIMITER =",";

    @Test
    public void createLeagueTableTest() throws IOException {
        String data=getData();
        List<List<String>> records = new ArrayList<>();
        List<Price> listPrices = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                String id = values[0];
                String istrumentName = values[1];
                Double bid = Double.valueOf(values[2]);
                Double ask = Double.valueOf(values[3]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
                LocalDateTime timestamp = LocalDateTime.parse(values[4], formatter);
                Price price = new Price(id,istrumentName,bid,ask, timestamp);
                listPrices.add(price);
                records.add(Arrays.asList(values));
            }
            logger.info(String.valueOf(records));
            listPrices.forEach(x -> logger.info(x.toString()));
        }
    }

    private String getData() {
        String result="106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001" +
                "\n107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002" +
                "\n108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002" +
                "\n109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100" +
                "\n110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110";
        logger.info(result);
        return result;
    }
}
