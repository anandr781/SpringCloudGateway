package com.example.demo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:apiGateway.properties")
public class ApiGatewayProperties {

    public String getBadRequest() {
        return badRequest;
    }

    @Value("${badRequest}")
    private String badRequest;
}
