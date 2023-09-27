package com.lomari.camel.components.jms;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JmsRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:orders")
                .log("${body}");
    }
}
