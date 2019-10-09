package com.electric.energyconsumption.client;

import com.electric.energyconsumption.dto.CounterDetailDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "counter-detail", url = "${counterdetail.api.url}")
public interface CounterDetailClient {

    @RequestLine("GET /counter?id={counterId}")
    @Headers("Accept: application/json")
    CounterDetailDTO getCounterDetails(@Param("counterId") String counterId);
}