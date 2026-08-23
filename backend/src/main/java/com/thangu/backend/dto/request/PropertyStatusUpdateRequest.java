package com.thangu.backend.dto.request;

import com.thangu.backend.common.enums.ListingStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyStatusUpdateRequest {
    private ListingStatus status;
}
