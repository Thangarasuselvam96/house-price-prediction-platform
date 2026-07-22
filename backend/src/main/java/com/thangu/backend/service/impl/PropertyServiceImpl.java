package com.thangu.backend.service.impl;

import com.thangu.backend.dto.request.PropertyRequest;
import com.thangu.backend.dto.request.PropertySearchRequest;
import com.thangu.backend.dto.response.PageResponse;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;
import com.thangu.backend.exception.ResourceNotFoundException;
import com.thangu.backend.mapper.PageMapper;
import com.thangu.backend.mapper.PropertyMapper;
import com.thangu.backend.repository.PropertyRepository;
import com.thangu.backend.security.CurrentUserService;
import com.thangu.backend.service.PropertyService;
import com.thangu.backend.specification.PropertySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository repository;
    private final PropertyMapper mapper;
    private final CurrentUserService currentUserService;
    private final AuthorizationService authorizationService;

    @Override
    public PropertyResponse save(PropertyRequest request) {
        User currentUser = currentUserService.currentUser();
        var property = mapper.toEntity(request);
        property.setSeller(currentUser);
        return mapper.toResponse(repository.save(property));
    }

    @Override
    public PropertyResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property not found"));
    }

    @Override
    public List<PropertyResponse> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public PageResponse<PropertyResponse> getAll(PropertySearchRequest request) {

        Specification<Property> spec = (root, query, cb) -> cb.conjunction();
        if(request.getCity() != null && !request.getCity().isBlank()) {
            spec = spec.and(PropertySpecification.hasCity(request.getCity()));
        }
        if (request.getMinPrice() != null) {
            spec = spec.and(
                    PropertySpecification.hasMinPrice(request.getMinPrice())
            );
        }

        if (request.getMaxPrice() != null) {
            spec = spec.and(
                    PropertySpecification.hasMaxPrice(request.getMaxPrice())
            );
        }

        if (request.getPropertyType() != null) {
            spec = spec.and(
                    PropertySpecification.hasPropertyType(
                            request.getPropertyType()
                    )
            );
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.fromString(request.getDirection()),request.getSortBy()));

        return PageMapper.from(
                repository.findAll(spec, pageable)
                        .map(mapper::toResponse)
        );
    }

    @Override
    public PropertyResponse update(Long id, PropertyRequest updatedProperty) {
        Property existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property not found"));

        authorizationService.canModifyProperty(existing);
        existing.setTitle(updatedProperty.getTitle());
        existing.setDescription(updatedProperty.getDescription());
        existing.setPrice(updatedProperty.getPrice());
        existing.setPincode(updatedProperty.getPincode());
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
                        new ResourceNotFoundException("Property not found"));
        authorizationService.canModifyProperty(property);
        repository.delete(property);
    }
}
