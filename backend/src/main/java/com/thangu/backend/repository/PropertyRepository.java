package com.thangu.backend.repository;

import com.thangu.backend.common.enums.ListingStatus;
import com.thangu.backend.common.enums.ListingType;
import com.thangu.backend.common.enums.PropertyType;
import com.thangu.backend.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// no implementation class. spring boot creates it at runtime
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByCity(String city);
    List<Property> findByPropertyType(PropertyType propertyType);
    List<Property> findByListingType(ListingType listingType);
    List<Property> findByListingStatus(ListingStatus listingStatus);
    List<Property> findByCityAndListingStatus(String city, String listingStatus);
}
