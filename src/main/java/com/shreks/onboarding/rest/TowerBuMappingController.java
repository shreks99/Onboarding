package com.shreks.onboarding.rest;

import com.shreks.onboarding.data.model.TowerBuMapping;
import com.shreks.onboarding.data.model.TowerBuMapping;
import com.shreks.onboarding.service.TowerBuMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/towerBuMapping")
public class TowerBuMappingController {
    @Autowired
    TowerBuMappingService towerBuMappingService;

    @GetMapping
    public List<TowerBuMapping> getAllTowerBuMapping() {
        return towerBuMappingService.getAllTowerBuMapping();
    }

    @PostMapping
    public void saveTowerBuMapping(@RequestBody TowerBuMapping towerBuMapping) {
        towerBuMappingService.saveTowerBuMapping(towerBuMapping);
    }

    @GetMapping(path="/{towerBuMappingId}")
    public TowerBuMapping getTowerBuMappingById(@PathVariable("towerBuMappingId") Integer towerBuMappingId) {
        return towerBuMappingService.getTowerBuMappingById(towerBuMappingId);
    }

    @DeleteMapping(path="/{towerBuMappingId}")
    public void deleteTowerBuMappingById(@PathVariable("towerBuMappingId") Integer towerBuMappingId) {
        towerBuMappingService.deleteTowerBuMappingById(towerBuMappingId);
    }

    @PutMapping
    public void updateTowerBuMapping(@RequestBody TowerBuMapping towerBuMapping) {
        towerBuMappingService.updateTowerBuMapping(towerBuMapping);
    }
}
