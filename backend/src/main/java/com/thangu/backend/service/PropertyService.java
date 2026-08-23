package com.thangu.backend.service;

import com.thangu.backend.dto.request.PropertyRequest;
import com.thangu.backend.dto.request.PropertySearchRequest;
import com.thangu.backend.dto.request.PropertyStatusUpdateRequest;
import com.thangu.backend.dto.response.PageResponse;
import com.thangu.backend.dto.response.PropertyResponse;

import java.util.List;

public interface PropertyService {
    PropertyResponse save(PropertyRequest property);
    PropertyResponse getById(Long id);
    List<PropertyResponse> getAll();
    PageResponse<PropertyResponse> getAll(PropertySearchRequest request);
    PropertyResponse update(Long id, PropertyRequest property);
    void delete(Long id);
    PropertyResponse updateStatus(Long propertyId, PropertyStatusUpdateRequest request);
}
