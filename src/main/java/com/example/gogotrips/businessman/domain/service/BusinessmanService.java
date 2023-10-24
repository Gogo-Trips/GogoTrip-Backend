package com.example.gogotrips.businessman.domain.service;

import com.example.gogotrips.businessman.resource.BusinessmanResource;
import com.example.gogotrips.businessman.resource.BusinessmanResponseResource;

import java.util.List;

public interface BusinessmanService {
    List<BusinessmanResponseResource> getAllBusinessmen();
    BusinessmanResponseResource getBusinessmanById(Long businessmanId);
    BusinessmanResponseResource createBusinessman(BusinessmanResource businessmanResource);
    BusinessmanResponseResource updateBusinessman(Long businessmanId, BusinessmanResource businessmanResource);
    void deleteBusinessman(Long businessmanId);
}
