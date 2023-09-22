package com.lomari.camel.components;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.ERROR;

@Component
@ConditionalOnProperty(name = "camel.hello.enabled", havingValue = "true")
public class HelloRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:greeting")
                .log(ERROR, "Hello ${body}")
                .choice()
                .when().simple("${body} contains 'Team'")
                .log(ERROR, "I like to work with teams")
                .otherwise()
                .log(ERROR, "Solo fighter :)")
                .end()
                .end();
    }
}
