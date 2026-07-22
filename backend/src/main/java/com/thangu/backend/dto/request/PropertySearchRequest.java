package com.thangu.backend.dto.request;

import com.thangu.backend.common.enums.PropertyType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertySearchRequest {
    private int page = 0;
    private int size = 20;
    private String sortBy = "createdAt";
    private String direction = "DESC";
    private String city;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private PropertyType propertyType;
}
