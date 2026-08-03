package com.thangu.backend.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyerDashboardResponse {
    private Long favoriteCount;
    private Long inquiryCount;
    List<FavoritePropertyResponse> recentFavorites;
    List<InquiryResponse> recentInquiries;
}
