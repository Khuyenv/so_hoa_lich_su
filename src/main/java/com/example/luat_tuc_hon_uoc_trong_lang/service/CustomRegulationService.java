package com.example.luat_tuc_hon_uoc_trong_lang.service;

import com.example.luat_tuc_hon_uoc_trong_lang.model.CustomRegulation;

import java.util.List;
import java.util.Optional;

public interface CustomRegulationService {

    List<CustomRegulation> getAllRegulations();

    Optional<CustomRegulation> getRegulationById(Long id);

    CustomRegulation saveRegulation(CustomRegulation regulation);

    void deleteRegulation(Long id);

    List<CustomRegulation> searchByKeyword(String keyword);

    List<CustomRegulation> filterByCategory(String category);
}
