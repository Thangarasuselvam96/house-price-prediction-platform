package com.thangu.backend.service.impl;

import com.thangu.backend.dto.request.PropertyRequest;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.entity.Property;
import com.thangu.backend.mapper.PropertyMapper;
import com.thangu.backend.repository.PropertyRepository;
import com.thangu.backend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository repository;
    private final PropertyMapper mapper;
    @Override
    public PropertyResponse save(PropertyRequest request) {
        var property = mapper.toEntity(request);
        return mapper.toResponse(repository.save(property));
    }

    @Override
    public PropertyResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() ->
                        new RuntimeException("Property not found"));
    }

    @Override
    public List<PropertyResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public PropertyResponse update(Long id, PropertyRequest updatedProperty) {
        Property existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Property not found"));

        existing.setTitle(updatedProperty.getTitle());
        existing.setDescription(updatedProperty.getDescription());
        existing.setPrice(updatedProperty.getPrice());
        existing.setCity(updatedProperty.getCity());
        existing.setState(updatedProperty.getState());
        existing.setAreaSqft(updatedProperty.getAreaSqft());
        existing.setPropertyType(updatedProperty.getPropertyType());
        existing.setListingType(updatedProperty.getListingType());
        existing.setListingStatus(updatedProperty.getListingStatus());

        return mapper.toResponse(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        Property property = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Property not found"));
        repository.delete(property);
    }
}
