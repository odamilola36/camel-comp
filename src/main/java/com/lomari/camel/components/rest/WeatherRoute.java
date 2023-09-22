package com.lomari.camel.components.rest;

import com.lomari.camel.dto.WeatherDto;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "java.rest.dsl", havingValue = "true")
public class WeatherRoute extends RouteBuilder {

    private final WeatherDataProvider repo;

    @Autowired
    public WeatherRoute(WeatherDataProvider repo) {
        this.repo = repo;
    }

    @Override
    public void configure() throws Exception {
        from("rest:GET:v1/weather/{city}?produces=application/json")
                .outputType(WeatherDto.class)
                .process(this::getData);
    }

    private void getData(Exchange exchange) {
        String city = exchange.getMessage().getHeader("city", String.class);
        WeatherDto weather = repo.getWeather(city);

        Message message = new DefaultMessage(exchange.getContext());
        message.setBody(weather);
        exchange.setMessage(message);
    }
}
