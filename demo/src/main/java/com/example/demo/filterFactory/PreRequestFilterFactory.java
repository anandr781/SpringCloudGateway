package com.example.demo.filterFactory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

public class PreRequestFilterFactory extends AbstractGatewayFilterFactory<PreRequestFilterFactory.Config> {

    public PreRequestFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
       return ((exchange, chain) -> {
          ServerHttpRequest mutatedRequest = exchange.getRequest();
          //TODO : Inspect Cookie collection for "token" cookie and inspect its contents (implement that in a separate package)

           // 1. Verify for token presence
           // 2. Deserialize to a User Object
           // 3. Inspect for expiry
           // 4. Forward the call to downstream web server

         return chain.filter(exchange.mutate().build()); //TODO : include request object after mutate() before build()
       });
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
