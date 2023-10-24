package com.example.gogotrips.businessman.mappers;

import com.example.gogotrips.businessman.domain.entity.Businessman;
import com.example.gogotrips.businessman.resource.BusinessmanResource;
import com.example.gogotrips.businessman.resource.BusinessmanResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessmanMapper {
    private final ModelMapper modelMapper;

    public BusinessmanMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Businessman resourceToEntity(BusinessmanResource businessmanResource) {
        return modelMapper.map(businessmanResource, Businessman.class);
    }

    public BusinessmanResource entityToResource(Businessman businessman) {
        return modelMapper.map(businessman, BusinessmanResource.class);
    }

    public BusinessmanResponseResource entityToResponseResource(Businessman businessman) {
        return modelMapper.map(businessman, BusinessmanResponseResource.class);
    }

    public List<Businessman> resourceListToEntityList(List<BusinessmanResource> businessmanResources) {
        return businessmanResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<BusinessmanResource> entityListToResourceList(List<Businessman> businessmen) {
        return businessmen
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<BusinessmanResponseResource> entityListToResponseResourceList(List<Businessman> businessmen) {
        return businessmen
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
