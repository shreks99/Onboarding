package com.shreks.onboarding.data.repository;

import com.shreks.onboarding.data.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity,Integer> {
}
