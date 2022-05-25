package com.shreks.onboarding.service;

import com.shreks.onboarding.data.entity.TowerBuMappingEntity;
import com.shreks.onboarding.data.model.TowerBuMapping;
import com.shreks.onboarding.data.repository.TowerBuMappingRepository;
import com.shreks.onboarding.util.DateTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TowerBuMappingServiceImpl implements TowerBuMappingService{
    final TowerBuMappingRepository towerBuMappingRepository;

    private final DateTimeUtil dateTimeUtil;

    public TowerBuMappingServiceImpl(TowerBuMappingRepository towerBuMappingRepository, DateTimeUtil dateTimeUtil) {
        this.towerBuMappingRepository = towerBuMappingRepository;
        this.dateTimeUtil = dateTimeUtil;
    }

    @Override
    @Transactional
    public List<TowerBuMapping> getAllTowerBuMapping() {
        List<TowerBuMappingEntity> towerBuMappingEntityList = (List<TowerBuMappingEntity>) towerBuMappingRepository.findAll();
        return towerBuMappingEntityList.stream().map(this::mapTowerBuMappingDTO).collect(Collectors.toList());
    }

    @Override
    public void saveTowerBuMapping(TowerBuMapping towerBuMapping) {
        TowerBuMappingEntity towerBuMappingEntity = mapTowerBuMappingDTOToEntity(towerBuMapping);
        towerBuMappingRepository.save(towerBuMappingEntity);
    }

    @Override
    public TowerBuMapping getTowerBuMappingById(Integer towerBuMappingId) {
        //Add try/catch block for exception handling
        TowerBuMappingEntity towerBuMappingEntity = towerBuMappingRepository.findById(towerBuMappingId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + towerBuMappingId));
        return mapTowerBuMappingDTO(towerBuMappingEntity);
    }

    @Override
    public void deleteTowerBuMappingById(Integer towerBuMappingId) {
        towerBuMappingRepository.deleteById(towerBuMappingId);
    }

    @Override
    @Transactional
    public void updateTowerBuMapping(TowerBuMapping towerBuMapping) {
        TowerBuMappingEntity updatedTowerBuMappingEntity = mapTowerBuMappingDTOToExistingEntity(towerBuMapping);
        towerBuMappingRepository.save(updatedTowerBuMappingEntity);
    }

    private TowerBuMappingEntity mapTowerBuMappingDTOToExistingEntity(TowerBuMapping towerBuMapping) {
        TowerBuMappingEntity towerBuMappingEntity = towerBuMappingRepository.findById(towerBuMapping.getBuTowerId()).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + towerBuMapping.getBuTowerId()));
        //Add modified time
        towerBuMappingEntity.setBusinessUnit(towerBuMapping.getBusinessUnit());
        towerBuMappingEntity.setTower(towerBuMapping.getTower());
        towerBuMappingEntity.setUpdateBy(towerBuMapping.getUpdateBy());
        towerBuMappingEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
        return  towerBuMappingEntity;
    }

    private TowerBuMappingEntity mapTowerBuMappingDTOToEntity(TowerBuMapping towerBuMapping) {
        TowerBuMappingEntity towerBuMappingEntity = new TowerBuMappingEntity();
        towerBuMappingEntity.setBusinessUnit(towerBuMapping.getBusinessUnit());
        towerBuMappingEntity.setTower(towerBuMapping.getTower());
        towerBuMappingEntity.setUpdateBy(towerBuMapping.getUpdateBy());
        towerBuMappingEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
        return towerBuMappingEntity;
    }

    private TowerBuMapping mapTowerBuMappingDTO(TowerBuMappingEntity towerBuMappingEntity) {
        TowerBuMapping towerBuMapping = new TowerBuMapping();
        towerBuMapping.setBuTowerId(towerBuMappingEntity.getBuTowerId());
        towerBuMapping.setBusinessUnit(towerBuMappingEntity.getBusinessUnit());
        towerBuMapping.setTower(towerBuMappingEntity.getTower());
        towerBuMapping.setUpdateBy(towerBuMappingEntity.getUpdateBy());
        towerBuMapping.setUpdateTime(towerBuMappingEntity.getUpdateTime());
        return towerBuMapping;
    }
}
