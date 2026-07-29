package com.thangu.backend.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InquiryResponse {
    private Long propertyId;
    private String message;
    private String status;
}
