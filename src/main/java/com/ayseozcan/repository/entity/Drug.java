package com.ayseozcan.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "drugs")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String drugName;
    private String companyName;
    private int stock;

    public Drug() {
    }

    public Drug(String drugName, String companyName, int stock) {
        this.drugName = drugName;
        this.companyName = companyName;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", drugName='" + drugName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", stock=" + stock +
                '}';
    }
}
