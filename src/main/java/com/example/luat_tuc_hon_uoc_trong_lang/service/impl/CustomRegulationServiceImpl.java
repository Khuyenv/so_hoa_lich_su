package com.example.luat_tuc_hon_uoc_trong_lang.service.impl;

import com.example.luat_tuc_hon_uoc_trong_lang.model.CustomRegulation;
import com.example.luat_tuc_hon_uoc_trong_lang.repository.CustomRegulationRepository;
import com.example.luat_tuc_hon_uoc_trong_lang.service.CustomRegulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomRegulationServiceImpl implements CustomRegulationService {

    private final CustomRegulationRepository customRegulationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CustomRegulation> getAllRegulations() {
        return customRegulationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomRegulation> getRegulationById(Long id) {
        return customRegulationRepository.findById(id);
    }

    @Override
    public CustomRegulation saveRegulation(CustomRegulation regulation) {
        return customRegulationRepository.save(regulation);
    }

    @Override
    public void deleteRegulation(Long id) {
        customRegulationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomRegulation> searchByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllRegulations();
        }
        return customRegulationRepository.findByTitleContainingIgnoreCase(keyword.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomRegulation> filterByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return getAllRegulations();
        }
        return customRegulationRepository.findByCategory(category.trim());
    }
}
