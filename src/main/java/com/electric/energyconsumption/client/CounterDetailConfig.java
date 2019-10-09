package com.electric.energyconsumption.client;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterDetailConfig {

    @Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }

}
