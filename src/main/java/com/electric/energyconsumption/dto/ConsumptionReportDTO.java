package com.electric.energyconsumption.dto;

import java.util.List;

public class ConsumptionReportDTO {

    private List<VillageConsumptionDTO> villages;

    public ConsumptionReportDTO() {
    }

    public ConsumptionReportDTO(List<VillageConsumptionDTO> villages) {
        this.villages = villages;
    }

    public List<VillageConsumptionDTO> getVillages() {
        return villages;
    }

    public void setVillages(List<VillageConsumptionDTO> villages) {
        this.villages = villages;
    }
}
