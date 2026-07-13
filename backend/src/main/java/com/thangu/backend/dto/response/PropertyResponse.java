package com.thangu.backend.dto.response;

import com.thangu.backend.common.enums.ListingStatus;
import com.thangu.backend.common.enums.ListingType;
import com.thangu.backend.common.enums.PropertyType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyResponse {

    private Long id;

    private String title;

    private String description;

    private PropertyType propertyType;

    private ListingType listingType;

    private BigDecimal price;

    private Integer areaSqft;

    private String city;

    private String state;

    private String pincode;

    private ListingStatus listingStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
