package com.example.luat_tuc_hon_uoc_trong_lang.repository;

import com.example.luat_tuc_hon_uoc_trong_lang.model.CustomRegulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRegulationRepository extends JpaRepository<CustomRegulation, Long> {

    List<CustomRegulation> findByCategory(String category);

    List<CustomRegulation> findByHistoricalPeriod(String historicalPeriod);

    List<CustomRegulation> findByTitleContainingIgnoreCase(String keyword);

    List<CustomRegulation> findByVillageNameContainingIgnoreCase(String villageName);

    List<CustomRegulation> findByProvinceContainingIgnoreCase(String province);
}
