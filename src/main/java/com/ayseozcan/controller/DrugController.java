package com.ayseozcan.controller;

import com.ayseozcan.dto.SaveDrugDto;
import com.ayseozcan.repository.entity.Drug;
import com.ayseozcan.service.DrugService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/drugs")
public class DrugController {

    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @PostMapping("/save")
    public ResponseEntity<Drug> save(@RequestBody @Valid SaveDrugDto dto) {
        return ResponseEntity.ok(drugService.save(dto));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Optional<Drug>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(drugService.findById(id));
    }

}
