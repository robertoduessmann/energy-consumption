package com.electric.energyconsumption.repository;

import com.electric.energyconsumption.model.EnergyConsumption;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface EnergyConsumptionRepository extends CrudRepository<EnergyConsumption, Long> {

    List<EnergyConsumption> findByDateAfter(Date date);
}
