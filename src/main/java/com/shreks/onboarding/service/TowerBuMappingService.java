package com.shreks.onboarding.service;

import com.shreks.onboarding.data.model.TowerBuMapping;

import java.util.List;

public interface TowerBuMappingService {
    List<TowerBuMapping> getAllTowerBuMapping();

    void saveTowerBuMapping(TowerBuMapping towerBuMapping);

    TowerBuMapping getTowerBuMappingById(Integer towerBuMappingId);

    void deleteTowerBuMappingById(Integer towerBuMappingId);

    void updateTowerBuMapping(TowerBuMapping towerBuMapping);
}
