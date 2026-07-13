package com.thangu.backend.service.impl;

import com.thangu.backend.entity.Property;
import com.thangu.backend.repository.PropertyRepository;
import com.thangu.backend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository repository;
    @Override
    public Property save(Property property) {
        return repository.save(property);
    }

    @Override
    public Property getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Property not found"));
    }

    @Override
    public List<Property> getAll() {
        return repository.findAll();
    }

    @Override
    public Property update(Long id, Property updatedProperty) {
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

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Property property = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Property not found"));
        repository.delete(property);
    }
}
