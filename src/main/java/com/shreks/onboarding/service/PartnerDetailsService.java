package com.shreks.onboarding.service;

import com.shreks.onboarding.data.model.PartnerDetails;
import com.shreks.onboarding.data.model.PartnerDetailsResponse;

import java.util.List;

public interface PartnerDetailsService {
    List<PartnerDetailsResponse> getAllPartnerDetails();

    void savePartnerDetails(PartnerDetails partnerDetails);

    PartnerDetailsResponse getPartnerDetailsById(Long partnerDetailsId);

    void deletePartnerDetailsById(Long partnerDetailsId);

    void updatePartnerDetails(PartnerDetails partnerDetails);
}
