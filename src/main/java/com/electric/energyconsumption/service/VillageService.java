package com.electric.energyconsumption.service;

import com.electric.energyconsumption.dto.CounterDetailDTO;
import com.electric.energyconsumption.model.Village;
import com.electric.energyconsumption.repository.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VillageService {

    @Autowired
    private VillageRepository villageRepository;
    @Autowired
    private CounterDetailService counterDetailService;

    public Village getVillage(Long id) {
        return villageRepository.findById(id).orElseGet(() -> create(id));
    }

    private Village create(Long id) {
        CounterDetailDTO counterDetails = counterDetailService.getCounterDetails(id.toString());
        Village village = new Village(id, counterDetails.getVillageName());
        return villageRepository.save(village);
    }
}
