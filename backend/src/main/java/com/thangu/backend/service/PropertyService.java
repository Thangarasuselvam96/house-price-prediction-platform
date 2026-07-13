package com.thangu.backend.service;

import com.thangu.backend.entity.Property;

import java.util.List;

public interface PropertyService {
    Property save(Property property);
    Property getById(Long id);
    List<Property> getAll();
    Property update(Long id, Property property);
    void delete(Long id);
}
