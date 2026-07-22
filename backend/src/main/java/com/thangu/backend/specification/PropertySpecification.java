package com.thangu.backend.specification;

import com.thangu.backend.common.enums.PropertyType;
import com.thangu.backend.entity.Property;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class PropertySpecification {
    private PropertySpecification() {}
    public static Specification<Property> hasCity(String city) {
        return (root, query, cb) -> {
            return cb.equal(
                    cb.lower(root.get("city")), city.toLowerCase()
            );
        };
    }

    public static Specification<Property> hasMinPrice(BigDecimal price) {
        return (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.get("price"), price);
        };
    }

    public static Specification<Property> hasMaxPrice(BigDecimal price) {

        return (root, query, cb) ->
                cb.lessThanOrEqualTo(
                        root.get("price"),
                        price
                );
    }

    public static Specification<Property> hasPropertyType(PropertyType propertyType) {

        return (root, query, cb) ->
                cb.equal(
                        root.get("propertyType"),
                        propertyType
                );
    }
}
