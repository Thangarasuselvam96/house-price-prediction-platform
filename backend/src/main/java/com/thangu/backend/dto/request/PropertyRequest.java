package com.thangu.backend.dto.request;


import com.thangu.backend.common.enums.ListingStatus;
import com.thangu.backend.common.enums.ListingType;
import com.thangu.backend.common.enums.PropertyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRequest {

    @Schema(description = "Property title", example = "Luxury villa in Chennai")
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private PropertyType propertyType;

    @NotNull
    private ListingType listingType;

    @NotNull
    private ListingStatus listingStatus;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer areaSqft;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String pincode;
}