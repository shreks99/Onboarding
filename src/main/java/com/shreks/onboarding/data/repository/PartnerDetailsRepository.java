package com.shreks.onboarding.data.repository;

import com.shreks.onboarding.data.entity.PartnerDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerDetailsRepository extends CrudRepository<PartnerDetailsEntity, Long> {
}