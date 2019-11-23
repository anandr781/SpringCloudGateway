package com.example.demo.filterFactory;

import org.springframework.cloud.gateway.filter.headers.XForwardedHeadersFilter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

//TODO : For now ignore this class , but demonstrates how to write custom predicates for request interception
@Deprecated
@Component
public class CustomRoutePredicateFactory extends AbstractRoutePredicateFactory<CustomRoutePredicateFactory.Config> {
    public CustomRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        //KEYPOINT: replace this lambda expression with any predicate object
        Predicate<ServerWebExchange> swe = s -> s.getRequest().getHeaders().containsKey(XForwardedHeadersFilter.X_FORWARDED_HOST_HEADER);
        return swe;
    }

    public static class Config {
        public Class getInClass() {
            return inClass;
        }

        public void setInClass(Class inClass) {
            this.inClass = inClass;
        }

        public Predicate getPredicate() {
            return predicate;
        }

        public void setPredicate(Predicate predicate) {
            this.predicate = predicate;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private Class inClass;

        private Predicate predicate;

    }
}
