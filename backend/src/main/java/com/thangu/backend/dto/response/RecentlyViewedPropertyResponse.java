package com.thangu.backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecentlyViewedPropertyResponse {
    private Long propertyId;
    private String title;
    private LocalDateTime lastViewedAt;
}
