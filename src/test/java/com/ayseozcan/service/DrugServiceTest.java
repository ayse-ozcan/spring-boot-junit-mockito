package com.ayseozcan.service;

import com.ayseozcan.dto.SaveDrugDto;
import com.ayseozcan.repository.IDrugRepository;
import com.ayseozcan.repository.entity.Drug;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Drug service test class")
public class DrugServiceTest {

    @Mock
    private IDrugRepository drugRepository;

    @InjectMocks
    private DrugService drugService;

    @Test
    @DisplayName("Saving drug")
    public void testSaveDrug() {

        // Given
        SaveDrugDto dto = new SaveDrugDto();
        dto.setDrugName("Sodium Chloride");
        dto.setCompanyName("Baxter Healthcare Corporation");
        dto.setStock(809);

        Drug savedDrug = new Drug();
        savedDrug.setId(1L);
        savedDrug.setDrugName(dto.getDrugName());
        savedDrug.setCompanyName(dto.getCompanyName());
        savedDrug.setStock(dto.getStock());

        // Mock the calls
        when(drugRepository.save(any(Drug.class))).thenReturn(savedDrug);

        // When
        Drug result = drugService.save(dto);

        // Then
//        ArgumentCaptor<Drug> drugArgumentCaptor = ArgumentCaptor.forClass(Drug.class);
//        verify(drugRepository).save(drugArgumentCaptor.capture());
//        Drug capturedDrug = drugArgumentCaptor.getValue();
//
//        assertEquals(dto.getDrugName(), capturedDrug.getDrugName());
//        assertEquals(dto.getCompanyName(), capturedDrug.getCompanyName());
//        assertEquals(dto.getStock(), capturedDrug.getStock());

        assertEquals(savedDrug.getId(), result.getId());
        assertEquals(savedDrug.getDrugName(), result.getDrugName());
        assertEquals(savedDrug.getCompanyName(), result.getCompanyName());
        assertEquals(savedDrug.getStock(), result.getStock());

        verify(drugRepository, times(1)).save(any(Drug.class));
    }

    @Test
    @DisplayName("Find drug by id - Success")
    public void testFindDrugById() {

        // Given
        Long drugId = 1L;
        Drug mockDrug = new Drug();
        mockDrug.setId(drugId);
        mockDrug.setDrugName("Bacitracin Zinc");
        mockDrug.setCompanyName("Dynarex Corporation");
        mockDrug.setStock(704);

        // Mock the calls
        when(drugRepository.findById(drugId)).thenReturn(Optional.of(mockDrug));

        // When
        Optional<Drug> result = drugService.findById(drugId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(mockDrug, result.get());

        verify(drugRepository, times(1)).findById(drugId);
    }

    @Test
    @DisplayName("Find drug by id - Not Found")
    public void testFindDrugById_NotFound() {

        // Given
        Long nonExistingDrugId = 2L;
        when(drugRepository.findById(nonExistingDrugId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> drugService.findById(nonExistingDrugId));
        assertEquals("Drug not found", exception.getMessage());

        verify(drugRepository, times(1)).findById(nonExistingDrugId);
    }
}