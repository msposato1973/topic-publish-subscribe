package com.memorynotfound.integration;

import com.memorynotfound.integration.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.memorynotfound.integration.ActiveMQConfig.ORDER_TOPIC;

@Component
public class OrderConsumer {

    private static final String COMMA_DELIMITER =",";

    private static final String ST_FORMAT = "dd-MM-yyyy HH:mm:ss:SSS";

    private  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ST_FORMAT);
    private static Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private PriceService priceService;

    @JmsListener(destination = ORDER_TOPIC, containerFactory = "topicListenerFactory")
    public void receiveTopicMessage(@Payload String data,
                                    @Headers MessageHeaders headers,
                                    Message message,
                                    Session session) throws IOException {

        log.info("received 1 <" + data + ">");

        List<List<String>> records = new ArrayList<>();
        List<Price> listPrices = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new StringReader(data));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Price price =  buildPriceObject( line.split(COMMA_DELIMITER));
                priceService.saveOrUpdate(price);
                listPrices.add(price);
                records.add(Arrays.asList(values));
            }
            log.info(String.valueOf(records));
            listPrices.forEach(x -> log.info(x.toString()));
        } catch (IOException io){
            log.info("IOException" + io.getMessage());
            io.printStackTrace();
        }


    }

    private Price buildPriceObject( String[] values){

       return  new Price(values[0],
               values[1],
               Double.valueOf(values[2]),
               Double.valueOf(values[3]),
               LocalDateTime.parse(values[4], formatter)

       );
    }
}
