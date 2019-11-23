package com.example.demo.filterFactory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

public class PostResponseGatewayFilterFactory extends AbstractGatewayFilterFactory<PostResponseGatewayFilterFactory.Config> {

    public PostResponseGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                //Manipulate the response in some way
                response.addCookie(
                        ResponseCookie.from("actualToken","This is the final UserContext object -> Symmetric Encrypted JWT payload")
                        .domain(exchange.getRequest().getURI().getRawAuthority().toString()).build()
                ); //TODO: productionalize this expression
            }));
        };
    }

    public static class Config {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;


    }
}
