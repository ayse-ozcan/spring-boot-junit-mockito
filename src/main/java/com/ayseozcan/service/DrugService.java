package com.ayseozcan.service;

import com.ayseozcan.dto.SaveDrugDto;
import com.ayseozcan.repository.IDrugRepository;
import com.ayseozcan.repository.entity.Drug;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DrugService {

    private final IDrugRepository drugRepository;

    public DrugService(IDrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public Drug save(SaveDrugDto dto) {

        Drug drug = new Drug();
        drug.setDrugName(dto.getDrugName());
        drug.setCompanyName(dto.getCompanyName());
        drug.setStock(dto.getStock());

        return drugRepository.save(drug);
    }

    public Optional<Drug> findById(Long id) {
        Optional<Drug> drug = drugRepository.findById(id);
        if (drug.isPresent()) {
            return drug;
        }
        throw new RuntimeException("Drug not found");
    }
}
