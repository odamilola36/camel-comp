package com.lomari.camel.components;


import com.lomari.camel.CamelApplication;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CamelApplication.class)
@MockEndpoints
@CamelSpringBootTest
public class HelloCamelRouteTest {

    @Autowired
    private ProducerTemplate template;

    @Test
    public void testMocksAreValid(){
        System.out.println("Sending 1");
        template.sendBody("direct:greeting", "Team");
        System.out.println("Sending 2");
        template.sendBody("direct:greeting", "Me");
    }
}
