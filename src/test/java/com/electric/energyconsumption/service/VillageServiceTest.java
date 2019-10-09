package com.electric.energyconsumption.service;

import com.electric.energyconsumption.dto.CounterDetailDTO;
import com.electric.energyconsumption.model.Village;
import com.electric.energyconsumption.repository.VillageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VillageServiceTest {

    private static final Long VILLAGE_ID = 1L;
    private static final String TEST_VILLAGE_NAME = "Village Test";

    @InjectMocks
    private VillageService service;
    @Mock
    private VillageRepository villageRepository;
    @Mock
    private CounterDetailService counterDetailService;

    @Test
    public void shouldGetVillageFromDB() {
        Village mockedVillage = new Village(VILLAGE_ID, TEST_VILLAGE_NAME);
        when(villageRepository.findById(VILLAGE_ID)).thenReturn(Optional.of(mockedVillage));

        Village response = service.getVillage(VILLAGE_ID);

        assertEquals(mockedVillage, response);
        verify(villageRepository).findById(VILLAGE_ID);
        verifyNoMoreInteractions(villageRepository);
        verifyZeroInteractions(counterDetailService);
    }

    @Test
    public void shouldSaveVillage() {
        CounterDetailDTO mockedCounterDetails = new CounterDetailDTO(VILLAGE_ID.toString(), TEST_VILLAGE_NAME);
        Village village = new Village(VILLAGE_ID, TEST_VILLAGE_NAME);
        when(villageRepository.findById(VILLAGE_ID)).thenReturn(Optional.empty());
        when(counterDetailService.getCounterDetails(VILLAGE_ID.toString())).thenReturn(mockedCounterDetails);
        when(villageRepository.save(village)).thenReturn(village);

        Village response = service.getVillage(VILLAGE_ID);

        verify(villageRepository).save(village);
        assertEquals(VILLAGE_ID, response.getId());
        assertEquals(TEST_VILLAGE_NAME, response.getName());
    }
}