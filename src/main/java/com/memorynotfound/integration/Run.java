package com.memorynotfound.integration;

import com.memorynotfound.integration.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Run  {


    private static Logger log = LoggerFactory.getLogger(Run.class);

    @Autowired
    private OrderSender orderSender;


    public void run() throws Exception {
        log.info("Spring Boot ActiveMQ Publish Subscribe Topic Configuration Example");

        for (int i = 0; i < 2; i++){
            String data = getData(i);
            orderSender.sendTopic(data);
        }

        log.info("Waiting for all ActiveMQ JMS Messages to be consumed");
        TimeUnit.SECONDS.sleep(3);
        // System.exit(-1);
    }


    private String getData(int i) {
        String result= i + "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001\n" +
                i + "107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002\n" +
                i + "108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002\n" +
                i + "109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100\n" +
                i + "110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110";

        return result;
    }


    public static void main(String[] args) throws Exception {
       ApplicationContext context= SpringApplication.run(Run.class, args);

      Run run= context.getBean(Run.class);
        run.run();

        PriceRepository repository = context.getBean(PriceRepository.class);
        List<Price> listPrices = repository.findAll();
        log.info("===================================");
        listPrices.forEach(x -> log.info(x.toString()));
    }


}
