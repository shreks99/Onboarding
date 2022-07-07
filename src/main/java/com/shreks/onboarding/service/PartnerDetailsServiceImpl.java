package com.shreks.onboarding.service;

import com.shreks.onboarding.data.entity.PartnerDetailsEntity;
import com.shreks.onboarding.data.entity.UserEntity;
import com.shreks.onboarding.data.model.PartnerDetails;
import com.shreks.onboarding.data.model.PartnerDetailsResponse;
import com.shreks.onboarding.data.repository.PartnerDetailsRepository;
import com.shreks.onboarding.data.repository.RoleRepository;
import com.shreks.onboarding.data.repository.UserRepository;
import com.shreks.onboarding.util.DateTimeUtil;
import com.shreks.onboarding.util.MapperUtil;
import com.shreks.onboarding.util.exception.ApplicationErrorCodes;
import com.shreks.onboarding.util.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerDetailsServiceImpl implements PartnerDetailsService {
    final PartnerDetailsRepository partnerDetailsRepository;
    final RoleRepository roleRepository;
    final UserRepository userRepository;

    private final DateTimeUtil dateTimeUtil;
    private final MapperUtil mapperUtil;

    public PartnerDetailsServiceImpl(PartnerDetailsRepository partnerDetailsRepository, RoleRepository roleRepository, UserRepository userRepository, DateTimeUtil dateTimeUtil, MapperUtil mapperUtil) {
        this.partnerDetailsRepository = partnerDetailsRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.dateTimeUtil = dateTimeUtil;
        this.mapperUtil = mapperUtil;
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
//       // PartnerDetailsEntity updatedPartnerDetailsEntity = mapPartnerDetailsDTOToExistingEntity(partnerDetails);
//        UserEntity updatedPartnerDetailsEntity = mapUserAndPartner(partnerDetails);
//        userRepository.save(updatedPartnerDetailsEntity);
        PartnerDetailsEntity updatedPartnerDetailsEntity = mapPartnerDetailsDTOToExistingEntity(partnerDetails);
        partnerDetailsRepository.save(updatedPartnerDetailsEntity);
    }


    private UserEntity mapUserAndPartner(PartnerDetails partnerDetails) {
        UserEntity userEntity = userRepository.findById(partnerDetails.getUserId()).get();
        userEntity.setUserName(partnerDetails.getUser().getUserName());
        userEntity.setPartnerDetailsEntity(mapPartnerDetailsDTOToEntity(partnerDetails));
        return userEntity;
    }

    private PartnerDetailsEntity mapPartnerDetailsDTOToExistingEntity(PartnerDetails partnerDetails) {
        if(partnerDetailsRepository.findById(partnerDetails.getUserId()).isPresent()){
            PartnerDetailsEntity partnerDetailsEntity = partnerDetailsRepository.findById(partnerDetails.getUserId()).get();
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

            partnerDetailsEntity.setUserEntity(mapperUtil.mapUserDTOToEntity(partnerDetails.getUser()));
            return partnerDetailsEntity;
        } else {
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationErrorCodes.USER_NOT_FOUND, "User Id not found");
        }
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

        partnerDetailsEntity.setUserEntity(mapperUtil.mapUserDTOToEntity(partnerDetails.getUser()));
        return partnerDetailsEntity;
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
         //   partnerDetails.setRoleId(partnerDetailsEntity.getUserEntity().getRoleEntity().getRoleId());
        return partnerDetails;
    }
}
