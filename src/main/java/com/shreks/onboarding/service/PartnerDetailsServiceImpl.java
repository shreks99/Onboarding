package com.shreks.onboarding.service;

import com.shreks.onboarding.data.entity.PartnerDetailsEntity;
import com.shreks.onboarding.data.entity.UserEntity;
import com.shreks.onboarding.data.model.PartnerDetails;
import com.shreks.onboarding.data.model.User;
import com.shreks.onboarding.data.repository.PartnerDetailsRepository;
import com.shreks.onboarding.util.DateTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerDetailsServiceImpl implements PartnerDetailsService {
    final PartnerDetailsRepository partnerDetailsRepository;

    private final DateTimeUtil dateTimeUtil;

    public PartnerDetailsServiceImpl(PartnerDetailsRepository partnerDetailsRepository, DateTimeUtil dateTimeUtil) {
        this.partnerDetailsRepository = partnerDetailsRepository;
        this.dateTimeUtil = dateTimeUtil;
    }

    @Override
    @Transactional
    public List<PartnerDetails> getAllPartnerDetails() {
        List<PartnerDetailsEntity> partnerDetailsEntityList = (List<PartnerDetailsEntity>) partnerDetailsRepository.findAll();
        return partnerDetailsEntityList.stream().map(this::mapPartnerDetailsDTO).collect(Collectors.toList());
    }

    @Override
    public void savePartnerDetails(PartnerDetails partnerDetails) {
        PartnerDetailsEntity partnerDetailsEntity = mapPartnerDetailsDTOToEntity(partnerDetails);
        partnerDetailsRepository.save(partnerDetailsEntity);
    }

    @Override
    public PartnerDetails getPartnerDetailsById(Long partnerDetailsId) {
        //Add try/catch block for exception handling
        PartnerDetailsEntity partnerDetailsEntity = partnerDetailsRepository.findById(partnerDetailsId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + partnerDetailsId));
        return mapPartnerDetailsDTO(partnerDetailsEntity);
    }

    @Override
    public void deletePartnerDetailsById(Long partnerDetailsId) {
        partnerDetailsRepository.deleteById(partnerDetailsId);
    }

    @Override
    @Transactional
    public void updatePartnerDetails(PartnerDetails partnerDetails) {
        PartnerDetailsEntity updatedPartnerDetailsEntity = mapPartnerDetailsDTOToExistingEntity(partnerDetails);
        partnerDetailsRepository.save(updatedPartnerDetailsEntity);
    }

    private PartnerDetailsEntity mapPartnerDetailsDTOToExistingEntity(PartnerDetails partnerDetails) {
        PartnerDetailsEntity partnerDetailsEntity = partnerDetailsRepository.findById(partnerDetails.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + partnerDetails.getUserId()));
        //Add modified time
        return  mapPartnerDetailsDTOToEntity(partnerDetails);
    }

    private PartnerDetailsEntity mapPartnerDetailsDTOToEntity(PartnerDetails partnerDetails) {
        PartnerDetailsEntity partnerDetailsEntity = new PartnerDetailsEntity();

        partnerDetailsEntity.setAddress(partnerDetails.getAddress());
        partnerDetailsEntity.setJobTitle(partnerDetails.getJobTitle());
        partnerDetailsEntity.setLocation(partnerDetails.getLocation());
        partnerDetailsEntity.setJoiningDate(partnerDetails.getJoiningDate());
        partnerDetailsEntity.setPersonalEmail(partnerDetails.getPersonalEmail());
        partnerDetailsEntity.setPositionNumber(partnerDetails.getPositionNumber());
        partnerDetailsEntity.setRequisitionNumber(partnerDetails.getRequisitionNumber());
        partnerDetailsEntity.setUpdateBy(partnerDetails.getUpdateBy());
        partnerDetailsEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());

        partnerDetailsEntity.setUserEntity(mapUserDTOToEntity(partnerDetails.getUser()));
        return partnerDetailsEntity;
    }

    private UserEntity mapUserDTOToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setLanId(user.getLanId());
        return userEntity;
    }

    private PartnerDetails mapPartnerDetailsDTO(PartnerDetailsEntity partnerDetailsEntity) {
        PartnerDetails partnerDetails = new PartnerDetails();
        partnerDetails.setUserId(partnerDetailsEntity.getUserId());
        partnerDetails.setJobTitle(partnerDetailsEntity.getJobTitle());
        partnerDetails.setAddress(partnerDetailsEntity.getAddress());
        partnerDetails.setLocation(partnerDetailsEntity.getLocation());
        partnerDetails.setJoiningDate(partnerDetailsEntity.getJoiningDate());
        partnerDetails.setPersonalEmail(partnerDetailsEntity.getPersonalEmail());
        partnerDetails.setPositionNumber(partnerDetailsEntity.getPositionNumber());
        partnerDetails.setRequisitionNumber(partnerDetailsEntity.getRequisitionNumber());
        partnerDetails.setUpdateBy(partnerDetailsEntity.getUpdateBy());
        partnerDetails.setUpdateTime(partnerDetailsEntity.getUpdateTime());
        return partnerDetails;
    }
}
