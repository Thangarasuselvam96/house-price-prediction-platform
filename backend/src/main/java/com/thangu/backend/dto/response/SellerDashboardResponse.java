package com.thangu.backend.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerDashboardResponse {
    private Long totalProperties;
    private Long activeProperties;
    private Long inActiveProperties;
    private Long totalFavorites;
    private Long totalInquiries;
    private List<InquiryResponse> recentInquiryResponse;
}
