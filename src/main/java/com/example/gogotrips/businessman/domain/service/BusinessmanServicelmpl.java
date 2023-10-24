package com.example.gogotrips.businessman.domain.service;

import com.example.gogotrips.businessman.domain.entity.Businessman;
import com.example.gogotrips.businessman.domain.persistence.BusinessmanRepository;
import com.example.gogotrips.businessman.mappers.BusinessmanMapper;
import com.example.gogotrips.businessman.resource.BusinessmanResource;
import com.example.gogotrips.businessman.resource.BusinessmanResponseResource;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessmanServicelmpl implements BusinessmanService{
    private final BusinessmanRepository businessmanRepository;

    private final BusinessmanMapper businessmanMapper;

    @Override
    public List<BusinessmanResponseResource> getAllBusinessmen() {
        List<Businessman> businessmen = businessmanRepository.findAll();
        return businessmanMapper.entityListToResponseResourceList(businessmen);
    }

    @Override
    public BusinessmanResponseResource getBusinessmanById(Long businessmanId) {
        Businessman businessman = businessmanRepository.findById(businessmanId)
                .orElseThrow(() -> new ResourceNotFoundException("Businessman not found with id: " + businessmanId));
        return businessmanMapper.entityToResponseResource(businessman);
    }

    @Transactional
    @Override
    public BusinessmanResponseResource createBusinessman(BusinessmanResource businessmanResource) {
        Businessman businessman = businessmanMapper.resourceToEntity(businessmanResource);
        businessman = businessmanRepository.save(businessman);

        return businessmanMapper.entityToResponseResource(businessman);
    }

    @Transactional
    @Override
    public BusinessmanResponseResource updateBusinessman(Long businessmanId, BusinessmanResource businessmanResource) {
        Optional<Businessman> optionalBusinessman = businessmanRepository.findById(businessmanId);

        if (optionalBusinessman.isPresent()) {
            Businessman businessman = optionalBusinessman.get();

            businessman.setEmail(businessmanResource.getEmail());
            businessman.setDirection(businessmanResource.getDirection());
            businessman.setServices(businessmanResource.getServices());
            businessman.setPhone(businessmanResource.getPhone());
            businessman.setCompanyName(businessmanResource.getCompanyName());
            businessman.setRuc(businessmanResource.getRuc());

            businessman = businessmanRepository.save(businessman);
            return businessmanMapper.entityToResponseResource(businessman);
        } else {
            throw new ResourceNotFoundException("Businessman not found with id: " + businessmanId);
        }
    }

    @Override
    @Transactional
    public void deleteBusinessman(Long businessmanId) {
        if (!businessmanRepository.existsById(businessmanId)) {
            throw new ResourceNotFoundException("Businessman not found with id: " + businessmanId);
        }

        businessmanRepository.deleteById(businessmanId);
    }
}
