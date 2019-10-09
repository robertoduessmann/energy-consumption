package com.electric.energyconsumption.controller;

import com.electric.energyconsumption.dto.EnergyCounterDTO;
import com.electric.energyconsumption.service.EnergyConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnergyConsumptionController {

    @Autowired
    private EnergyConsumptionService energyConsumptionService;

    @PostMapping("counter_callback")
    public ResponseEntity addConsumption(@RequestBody EnergyCounterDTO energyCounter) {
        energyConsumptionService.addConsumption(energyCounter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("consumption_report")
    public ResponseEntity getConsumption(@RequestParam(value = "duration") String duration){
        return ResponseEntity.ok(energyConsumptionService.getConsumption(duration));
    }
}

