package com.ayseozcan.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveDrugDto(@NotBlank(message = "Drug name cannot be blank") String drugName,
                          @NotBlank(message = "Company name cannot be blank") String companyName,
                          int stock) {

    public SaveDrugDto {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }

}