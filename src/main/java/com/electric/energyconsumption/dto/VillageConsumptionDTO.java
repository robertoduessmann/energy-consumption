package com.electric.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class VillageConsumptionDTO {

    @JsonProperty("village_name")
    private String villageName;
    private BigDecimal consumption;

    public VillageConsumptionDTO() {
    }

    public VillageConsumptionDTO(String villageName, BigDecimal consumption) {
        this.villageName = villageName;
        this.consumption = consumption;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }
}
