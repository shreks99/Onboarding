package com.shreks.onboarding.service;

import com.shreks.onboarding.data.model.PartnerDetails;

import java.util.List;

public interface PartnerDetailsService {
    List<PartnerDetails> getAllPartnerDetails();

    void savePartnerDetails(PartnerDetails partnerDetails);

    PartnerDetails getPartnerDetailsById(Long partnerDetailsId);

    void deletePartnerDetailsById(Long partnerDetailsId);

    void updatePartnerDetails(PartnerDetails partnerDetails);
}
