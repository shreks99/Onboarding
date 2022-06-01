package com.shreks.onboarding.service;

import com.shreks.onboarding.data.entity.PartnerDetailsEntity;
import com.shreks.onboarding.data.entity.UserEntity;
import com.shreks.onboarding.data.model.PartnerDetails;
import com.shreks.onboarding.data.model.PartnerDetailsResponse;
import com.shreks.onboarding.data.model.User;
import com.shreks.onboarding.data.repository.PartnerDetailsRepository;
import com.shreks.onboarding.data.repository.RoleRepository;
import com.shreks.onboarding.util.DateTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerDetailsServiceImpl implements PartnerDetailsService {
    final PartnerDetailsRepository partnerDetailsRepository;
    final RoleRepository roleRepository;

    private final DateTimeUtil dateTimeUtil;

    public PartnerDetailsServiceImpl(PartnerDetailsRepository partnerDetailsRepository, RoleRepository roleRepository, DateTimeUtil dateTimeUtil) {
        this.partnerDetailsRepository = partnerDetailsRepository;
        this.roleRepository = roleRepository;
        this.dateTimeUtil = dateTimeUtil;
    }

    @Override
    @Transactional
    public List<PartnerDetailsResponse> getAllPartnerDetails() {
        List<PartnerDetailsEntity> partnerDetailsEntityList = (List<PartnerDetailsEntity>) partnerDetailsRepository.findAll();
        return partnerDetailsEntityList.stream().map(this::mapPartnerDetailsDTO).collect(Collectors.toList());
    }

    @Override
    public void savePartnerDetails(PartnerDetails partnerDetails) {
        PartnerDetailsEntity partnerDetailsEntity = mapPartnerDetailsDTOToEntity(partnerDetails);
        partnerDetailsRepository.save(partnerDetailsEntity);
    }

    @Override
    public PartnerDetailsResponse getPartnerDetailsById(Long partnerDetailsId) {
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
        partnerDetailsEntity.setDropoutReason(partnerDetails.getDropoutReason());
        partnerDetailsEntity.setPhone(partnerDetails.getPhone());
        partnerDetailsEntity.setStatus(partnerDetails.getStatus());
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
        userEntity.setUpdateBy(user.getUpdateBy());
        userEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
        userEntity.setRoleEntity(roleRepository.findById(user.getRole().getRoleId()).orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + user.getRole().getRoleId())));
        return userEntity;
    }

    private PartnerDetailsResponse mapPartnerDetailsDTO(PartnerDetailsEntity partnerDetailsEntity) {
        PartnerDetailsResponse partnerDetails = new PartnerDetailsResponse();
        partnerDetails.setUserId(partnerDetailsEntity.getUserId());
        partnerDetails.setDropoutReason(partnerDetailsEntity.getDropoutReason());
        partnerDetails.setEmail(partnerDetailsEntity.getPersonalEmail());
        partnerDetails.setAddress(partnerDetailsEntity.getAddress());
        partnerDetails.setJoiningDate(partnerDetailsEntity.getJoiningDate());
        partnerDetails.setPhone(partnerDetailsEntity.getPhone());
        partnerDetails.setStatus(partnerDetailsEntity.getStatus());
        partnerDetails.setPartnerName(partnerDetailsEntity.getUserEntity().getUserName());
        partnerDetails.setLanId(partnerDetailsEntity.getUserEntity().getLanId());
            partnerDetails.setRoleId(partnerDetailsEntity.getUserEntity().getRoleEntity().getRoleId());
        return partnerDetails;
    }
}
