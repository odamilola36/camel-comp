package com.lomari.camel.components;


import com.lomari.camel.CamelApplication;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CamelApplication.class)
@EnableAutoConfiguration
@CamelSpringBootTest
@MockEndpoints
class SedaRouteTest {

    @Autowired
    private ProducerTemplate template;

    @Test
    public void test(){

    }

}