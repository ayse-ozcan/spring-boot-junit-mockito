package com.ayseozcan.service;

import com.ayseozcan.dto.SaveDrugDto;
import com.ayseozcan.repository.DrugRepository;
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
class DrugServiceTest {

    @Mock
    private DrugRepository drugRepository;

    @InjectMocks
    private DrugService drugService;

    @Test
    @DisplayName("Saving drug")
    void shouldSaveDrugSuccessfully() {

        // Given
        SaveDrugDto dto = new SaveDrugDto("Sodium Chloride", "Baxter Healthcare Corporation", 809);

        Drug savedDrug = new Drug();
        savedDrug.setId(1L);
        savedDrug.setDrugName(dto.drugName());
        savedDrug.setCompanyName(dto.companyName());
        savedDrug.setStock(dto.stock());

        // Mock the calls
        when(drugRepository.save(any(Drug.class))).thenReturn(savedDrug);

        // When
        Drug result = drugService.save(dto);

        // Then
//        ArgumentCaptor<Drug> drugArgumentCaptor = ArgumentCaptor.forClass(Drug.class);
//        verify(drugRepository).save(drugArgumentCaptor.capture());
//        Drug capturedDrug = drugArgumentCaptor.getValue();
//
//        assertEquals(dto.drugName(), capturedDrug.getDrugName());
//        assertEquals(dto.companyName(), capturedDrug.getCompanyName());
//        assertEquals(dto.stock(), capturedDrug.getStock());

        assertEquals(savedDrug.getId(), result.getId());
        assertEquals(savedDrug.getDrugName(), result.getDrugName());
        assertEquals(savedDrug.getCompanyName(), result.getCompanyName());
        assertEquals(savedDrug.getStock(), result.getStock());

        verify(drugRepository).save(any(Drug.class));
    }

    @Test
    @DisplayName("Find drug by id - Success")
    void shouldFindDrugById_Success() {

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

        verify(drugRepository).findById(drugId);
    }

    @Test
    @DisplayName("Find drug by id - Not Found")
    void shouldFindDrugById_NotFound() {

        // Given
        Long nonExistingDrugId = 2L;
        when(drugRepository.findById(nonExistingDrugId)).thenReturn(Optional.empty());

        // When
        Optional<Drug> result = drugService.findById(nonExistingDrugId);

        // Then
        assertEquals(Optional.empty(), result);

        verify(drugRepository).findById(nonExistingDrugId);
    }
}