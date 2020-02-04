package com.example.demo.filterFactory;

import com.example.registries.CustomMetricsRegistry;
import com.example.registries.ICustomMetricsRegistry;
import com.example.registries.Maths;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeanConfiguration {

    @Bean("mathService")
    public Maths maths(){
        return new Maths();
    }

    @Autowired
    //Refer : https://micrometer.io/docs/registry/prometheus for integration with Prometheus
    private PrometheusMeterRegistry pmr;

    @Bean
    public ICustomMetricsRegistry PrometheusMeterRegistry(){
        return  new CustomMetricsRegistry(pmr);
    }
}
