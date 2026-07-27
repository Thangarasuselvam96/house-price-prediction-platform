package com.thangu.backend.dto.response;

import com.thangu.backend.common.enums.PropertyType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritePropertyResponse {
    private Long propertyId;
    private String title;
    private BigDecimal price;
    private String city;
    private PropertyType propertyType;
    private LocalDateTime favoriteDate;
}
