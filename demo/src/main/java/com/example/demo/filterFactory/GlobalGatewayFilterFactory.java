package com.example.demo.filterFactory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class GlobalGatewayFilterFactory extends AbstractGatewayFilterFactory<GlobalGatewayFilterFactory.Config> {

    public GlobalGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            /*
            KEYPOINT :
             Request forwarded from a Gateway needs to be flagged correctly for web security reasons
             Proxy headers in Http request
              -> Via
              -> X-Forwarded-For (ClientIP), X-Forwarded-Host & Proto [Enabled in application.yml]
            */
            ServerHttpRequest request = exchange.getRequest();
            request.mutate().header("Via", "1.1 SpringCG")
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config {
        private String name;


        public Config() {
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

