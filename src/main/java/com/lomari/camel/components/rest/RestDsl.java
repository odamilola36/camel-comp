package com.lomari.camel.components.rest;

import com.lomari.camel.dto.WeatherDto;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "camel.hello.enabled", havingValue = "true")
public class RestDsl extends RouteBuilder {

    private final WeatherDataProvider repo;

    public RestDsl(WeatherDataProvider data) {
        this.repo = data;
    }


    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                        .bindingMode(RestBindingMode.auto);

        rest()
                .consumes("application/json")
                .produces("application.json")
                .get("/weather/{city}").outType(WeatherRoute.class).to("direct:get-weather")
                .post().type(WeatherDto.class).to("direct:save-weather");

        from("direct:get-weather")
                .process(this::getWeather);
        from("direct:save-weather")
                .process(this::saveWeather);

    }

    private void getWeather(Exchange exchange) {
        String city = exchange.getMessage().getHeader("city", String.class);
        WeatherDto weather = repo.getWeather(city);

        Message message = new DefaultMessage(exchange.getContext());
        message.setBody(weather);
        exchange.setMessage(message);
    }

    private void saveWeather(Exchange exchange) {
        repo.setCurrentWeather(exchange.getMessage().getBody(WeatherDto.class));
    }
}
