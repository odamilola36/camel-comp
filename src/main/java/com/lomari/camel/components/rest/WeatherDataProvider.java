package com.lomari.camel.components.rest;


import com.lomari.camel.dto.WeatherDto;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WeatherDataProvider {

    private static final Map<String, WeatherDto> data = new ConcurrentHashMap<>();

    public WeatherDataProvider() {
        var dto = WeatherDto.builder()
                .city("London")
                .unit("C")
                .temp("10")
                .receivedTime(new Date().toString())
                .id(1)
                .build();
        data.put("LONDON", dto);
    }

    public WeatherDto getWeather(String city){
        return data.get(city);
    }

    public void setCurrentWeather(WeatherDto dto){
        dto.setReceivedTime(new Date().toString());
        data.put(dto.getCity().toUpperCase(), dto);
    }
}
