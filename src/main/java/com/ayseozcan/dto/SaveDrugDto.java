package com.ayseozcan.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SaveDrugDto {

    @NotEmpty(message = "Name field cannot be empty")
    private String drugName;

    @NotEmpty(message = "Company field cannot be empty")
    private String companyName;
    @NotNull(message = "Stock field cannot be null")
    private int stock;

    public String getDrugName() {
        return drugName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getStock() {
        return stock;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
