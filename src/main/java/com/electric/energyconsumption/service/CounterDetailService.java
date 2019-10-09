package com.electric.energyconsumption.service;

import com.electric.energyconsumption.client.CounterDetailClient;
import com.electric.energyconsumption.dto.CounterDetailDTO;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CounterDetailService.class);
    private static final String UNKNOWN_VILLAGE_NAME = "UNKNOWN";

    @Autowired
    private CounterDetailClient counterDetailClient;

    public CounterDetailDTO getCounterDetails(String counterId) {
        try {
            return counterDetailClient.getCounterDetails(counterId);
        } catch (FeignException e) {
            LOGGER.warn("Counter details not found, returning default details for counterId: " + counterId);
            return new CounterDetailDTO(counterId, UNKNOWN_VILLAGE_NAME);
        }
    }
}
