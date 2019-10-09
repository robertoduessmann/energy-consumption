package com.electric.energyconsumption.service;

import com.electric.energyconsumption.dto.ConsumptionReportDTO;
import com.electric.energyconsumption.dto.EnergyCounterDTO;
import com.electric.energyconsumption.dto.VillageConsumptionDTO;
import com.electric.energyconsumption.model.EnergyConsumption;
import com.electric.energyconsumption.model.Village;
import com.electric.energyconsumption.repository.EnergyConsumptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnergyConsumptionServiceTest {

    private static final Long COUNTER_ID = 1L;
    private static final Long COUNTER_ID_2 = 356L;
    private static final String TEST_VILLAGE_NAME = "Village Test";
    private static final String TEST_VILLAGE_NAME_2 = "Village Test 2";
    private static final BigDecimal AMOUNT_CONSUMPTION = BigDecimal.valueOf(15);
    private static final String DURATION = "24h";

    @InjectMocks
    private EnergyConsumptionService service;
    @Mock
    private EnergyConsumptionRepository energyConsumptionRepository;
    @Mock
    private VillageService villageService;
    @Captor
    private ArgumentCaptor<EnergyConsumption> energyConsumptionCaptor;

    @Test
    public void shouldAddConsumption() {
        EnergyCounterDTO energyCounter = new EnergyCounterDTO(COUNTER_ID.toString(), AMOUNT_CONSUMPTION);
        Village village = aVillage(COUNTER_ID, TEST_VILLAGE_NAME);
        when(villageService.getVillage(COUNTER_ID)).thenReturn(village);

        service.addConsumption(energyCounter);

        verify(energyConsumptionRepository).save(energyConsumptionCaptor.capture());
        EnergyConsumption energyConsumption = energyConsumptionCaptor.getValue();
        assertEquals(AMOUNT_CONSUMPTION, energyConsumption.getConsumption());
        assertEquals(village, energyConsumption.getVillage());
    }

    @Test
    public void shoudlGetConsumption() {
        Village village1 = aVillage(COUNTER_ID, TEST_VILLAGE_NAME);
        Village village2 = aVillage(COUNTER_ID_2, TEST_VILLAGE_NAME_2);
        EnergyConsumption energyConsumption1 = anEnergyConsumption(village1, BigDecimal.TEN);
        EnergyConsumption energyConsumption2 = anEnergyConsumption(village1, BigDecimal.ONE);
        EnergyConsumption energyConsumption3 = anEnergyConsumption(village2, AMOUNT_CONSUMPTION);
        when(energyConsumptionRepository.findByDateAfter(any(Date.class)))
                .thenReturn(asList(energyConsumption1, energyConsumption2, energyConsumption3));

        ConsumptionReportDTO consumption = service.getConsumption(DURATION);

        List<VillageConsumptionDTO> villageConsumptions = consumption.getVillages();
        assertEquals(2, villageConsumptions.size());
        assertEquals(TEST_VILLAGE_NAME_2, villageConsumptions.get(0).getVillageName());
        assertEquals(AMOUNT_CONSUMPTION, villageConsumptions.get(0).getConsumption());
        assertEquals(TEST_VILLAGE_NAME, villageConsumptions.get(1).getVillageName());
        assertEquals(BigDecimal.valueOf(11), villageConsumptions.get(1).getConsumption());
    }

    private EnergyConsumption anEnergyConsumption(Village village, BigDecimal amount) {
        return new EnergyConsumption(amount, village);
    }

    private Village aVillage(Long villageId, String villageName) {
        return new Village(villageId, villageName);
    }

}