package com.ayseozcan.repository;

import com.ayseozcan.repository.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDrugRepository extends JpaRepository<Drug, Long> {
}
