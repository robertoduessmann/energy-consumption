package com.electric.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CounterDetailDTO {

    private String id;
    @JsonProperty("village_name")
    private String villageName;

    public CounterDetailDTO(String id, String villageName) {
        this.id = id;
        this.villageName = villageName;
    }

    public String getId() {
        return id;
    }

    public String getVillageName() {
        return villageName;
    }
}
