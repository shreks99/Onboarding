package com.shreks.onboarding.data.repository;

import com.shreks.onboarding.data.entity.TowerBuMappingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TowerBuMappingRepository extends CrudRepository<TowerBuMappingEntity, Integer> {
}
