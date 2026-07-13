package com.thangu.backend.mapper;

import com.thangu.backend.dto.request.PropertyRequest;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.entity.Property;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    Property toEntity(PropertyRequest request);
    PropertyResponse toResponse(Property property);
    List<PropertyResponse> toResponseList(List<Property> properties);
}
