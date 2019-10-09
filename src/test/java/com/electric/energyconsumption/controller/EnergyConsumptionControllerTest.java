package com.electric.energyconsumption.controller;

import com.electric.energyconsumption.dto.ConsumptionReportDTO;
import com.electric.energyconsumption.dto.EnergyCounterDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnergyConsumptionControllerTest {

    private static final Long COUNTER_ID = 1L;
    private static final BigDecimal AMOUNT_CONSUMPTION = BigDecimal.valueOf(15);
    private static final String UNKNOWN_VILLAGE_NAME = "UNKNOWN";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldAddConsumption() {
        ResponseEntity response = restTemplate
                .postForEntity("/counter_callback", aEnergyCounterRequest(BigDecimal.ZERO), EnergyCounterDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldGetConsumption() {
        restTemplate.postForEntity("/counter_callback", aEnergyCounterRequest(AMOUNT_CONSUMPTION), EnergyCounterDTO.class);

        ResponseEntity<ConsumptionReportDTO> response = restTemplate
                .getForEntity("/consumption_report?duration=24h", ConsumptionReportDTO.class);
        ConsumptionReportDTO consumptionReport = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, consumptionReport.getVillages().size());
        assertEquals(UNKNOWN_VILLAGE_NAME, consumptionReport.getVillages().get(0).getVillageName());
        assertThat(consumptionReport.getVillages().get(0).getConsumption(), comparesEqualTo(AMOUNT_CONSUMPTION));
    }

    private EnergyCounterDTO aEnergyCounterRequest(BigDecimal amountConsumption) {
        return new EnergyCounterDTO(COUNTER_ID.toString(), amountConsumption);
    }

}