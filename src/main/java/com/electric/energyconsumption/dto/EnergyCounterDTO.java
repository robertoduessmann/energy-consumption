package com.electric.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class EnergyCounterDTO {

    @JsonProperty("counter_id")
    private String counterId;
    private BigDecimal amount;

    public EnergyCounterDTO(String counterId, BigDecimal amount) {
        this.counterId = counterId;
        this.amount = amount;
    }

    public String getCounterId() {
        return counterId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
