package com.ayseozcan.dto;

public record SaveDrugDto(String drugName, String companyName, int stock) {

    public SaveDrugDto {
        if (drugName.isBlank()) {
            throw new IllegalArgumentException("Drug name cannot be blank");
        }
        if (companyName.isBlank()) {
            throw new IllegalArgumentException("Company name cannot be blank");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }

}