package com.electric.energyconsumption.service;

import com.electric.energyconsumption.dto.ConsumptionReportDTO;
import com.electric.energyconsumption.dto.EnergyCounterDTO;
import com.electric.energyconsumption.dto.VillageConsumptionDTO;
import com.electric.energyconsumption.model.EnergyConsumption;
import com.electric.energyconsumption.model.Village;
import com.electric.energyconsumption.repository.EnergyConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EnergyConsumptionService {

    @Autowired
    private EnergyConsumptionRepository energyConsumptionRepository;
    @Autowired
    private VillageService villageService;

    public void addConsumption(EnergyCounterDTO energyCounter) {
        Village village = villageService.getVillage(Long.valueOf(energyCounter.getCounterId()));
        EnergyConsumption energyConsumption = new EnergyConsumption(energyCounter.getAmount(), village);
        energyConsumptionRepository.save(energyConsumption);
    }

    public ConsumptionReportDTO getConsumption(String duration) {
        Map<Village, BigDecimal> villageToConsumption = energyConsumptionRepository.findByDateAfter(getTimeFilter(duration))
                .stream()
                .collect(Collectors.groupingBy(EnergyConsumption::getVillage,
                        Collectors.reducing(BigDecimal.ZERO, EnergyConsumption::getConsumption, BigDecimal::add)));

        List<VillageConsumptionDTO> villages = villageToConsumption.entrySet().stream()
                .map(v -> new VillageConsumptionDTO(v.getKey().getName(), v.getValue()))
                .collect(Collectors.toList());

        return new ConsumptionReportDTO(villages);
    }

    private Date getTimeFilter(String duration) {
        return Date.from(LocalDateTime.now().minusHours(Long.valueOf(duration.replaceAll("\\D+", "")))
                .atZone(ZoneId.systemDefault()).toInstant());
    }

}
