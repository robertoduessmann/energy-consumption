package com.electric.energyconsumption.service;

import com.electric.energyconsumption.client.CounterDetailClient;
import com.electric.energyconsumption.dto.CounterDetailDTO;
import feign.FeignException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CounterDetailServiceTest {

    private static final String COUNTER_ID = "1";
    private static final String UNKNOWN_VILLAGE_NAME = "UNKNOWN";
    private static final String TEST_VILLAGE_NAME = "Village Test";

    @InjectMocks
    private CounterDetailService service;
    @Mock
    private CounterDetailClient counterDetailClient;

    @Test
    public void shouldGetCounterDetails() {
        CounterDetailDTO mockedCounterDetails = new CounterDetailDTO(COUNTER_ID, TEST_VILLAGE_NAME);
        when(counterDetailClient.getCounterDetails(COUNTER_ID)).thenReturn(mockedCounterDetails);

        CounterDetailDTO response = service.getCounterDetails(COUNTER_ID);

        assertEquals(mockedCounterDetails, response);
    }

    @Test
    public void shouldGetDefailtCounterDetails() {
        doThrow(FeignException.class).when(counterDetailClient).getCounterDetails(COUNTER_ID);

        CounterDetailDTO response = service.getCounterDetails(COUNTER_ID);

        assertEquals(COUNTER_ID, response.getId());
        assertEquals(UNKNOWN_VILLAGE_NAME, response.getVillageName());
    }
}