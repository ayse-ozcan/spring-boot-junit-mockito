package com.ayseozcan.service;

import com.ayseozcan.dto.SaveDrugDto;
import com.ayseozcan.repository.DrugRepository;
import com.ayseozcan.repository.entity.Drug;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DrugService {

    private final DrugRepository drugRepository;

    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public Drug save(SaveDrugDto dto) {

        Drug drug = new Drug();
        drug.setDrugName(dto.drugName());
        drug.setCompanyName(dto.companyName());
        drug.setStock(dto.stock());

        return drugRepository.save(drug);
    }

    public Optional<Drug> findById(Long id) {
        return drugRepository.findById(id);
    }
}
