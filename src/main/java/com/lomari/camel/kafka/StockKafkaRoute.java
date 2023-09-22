package com.lomari.camel.kafka;


import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StockKafkaRoute extends RouteBuilder {

    final String KAFKA_ENDPOINT = "kafka:%s?brokers=localhost:29092";

    @Override
    public void configure() throws Exception {
        fromF(KAFKA_ENDPOINT, "stock-live")
                .log(LoggingLevel.ERROR, "[${header.kafka.OFFSET}] [${body}]")
                .bean(StockPriceEnricher.class)
                .toF(KAFKA_ENDPOINT, "stock-audit")
        ;
    }

    private class StockPriceEnricher {
        public String enrich(String stockPrice){
            return stockPrice + "." + new Date();
        }
    }
}
