package com.shreks.onboarding.rest;

import com.shreks.onboarding.data.model.PartnerDetails;
import com.shreks.onboarding.data.model.PartnerDetailsResponse;
import com.shreks.onboarding.service.PartnerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partner")
public class PartnerDetailsController {

    final PartnerDetailsService partnerDetailsService;

    @Autowired
    public PartnerDetailsController(PartnerDetailsService partnerDetailsService) {
        this.partnerDetailsService = partnerDetailsService;
    }

    @GetMapping
    public List<PartnerDetailsResponse> getAllPartnerDetails() {
        return partnerDetailsService.getAllPartnerDetails();
    }

    @PostMapping
    public void savePartnerDetails(@RequestBody PartnerDetails partnerDetails) {
        partnerDetailsService.savePartnerDetails(partnerDetails);
    }

    @GetMapping(path="/{partnerDetailsId}")
    public PartnerDetailsResponse getPartnerDetailsById(@PathVariable("partnerDetailsId") Long partnerDetailsId) {
        return partnerDetailsService.getPartnerDetailsById(partnerDetailsId);
    }

    @DeleteMapping(path="/{partnerDetailsId}")
    public void deletePartnerDetailsById(@PathVariable("partnerDetailsId") Long partnerDetailsId) {
        partnerDetailsService.deletePartnerDetailsById(partnerDetailsId);
    }

    @PutMapping
    public void updatePartnerDetails(@RequestBody PartnerDetails partnerDetails) {
        partnerDetailsService.updatePartnerDetails(partnerDetails);
    }
}
