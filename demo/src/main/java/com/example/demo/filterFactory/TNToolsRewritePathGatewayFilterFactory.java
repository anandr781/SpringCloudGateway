package com.example.demo.filterFactory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;


@Component
public class TNToolsRewritePathGatewayFilterFactory extends RewritePathGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Config config) {
        String replacement = config.getReplacement().replace("$\\", "$");
        GatewayFilter filter = new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange,
                                     GatewayFilterChain chain) {

                //KEYPOINT : Receive the request
                ServerHttpRequest req = exchange.getRequest();
                addOriginalRequestUrl(exchange, req.getURI());
                String path = req.getURI().getRawPath(); // .getURI() -> http://blahblah/foo/bar and .getRawPath() -> /foo/bar
                String newPath = path.replaceAll(config.getRegexp(), replacement); //this produces /bar

                //TODO : Log the SrcIP : req.remoteAddress , OldPath - path , NewRewrittenPath - newPath
                System.out.print(req.getRemoteAddress().toString() + ","+ path+ ","+newPath);
                ServerHttpRequest request = req.mutate().path(newPath).build(); //mutate and new copy of the request object

                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI()); //set it as the actual request by Gateway

                return chain.filter(exchange.mutate().request(request).build());
            }
        };
        return filter;
    }

}
