package com.thangu.backend.service.impl;

import com.thangu.backend.dto.response.SellerDashboardResponse;
import com.thangu.backend.entity.User;
import com.thangu.backend.mapper.InquiryMapper;
import com.thangu.backend.repository.FavoritePropertyRepository;
import com.thangu.backend.repository.InquiryRepository;
import com.thangu.backend.repository.PropertyRepository;
import com.thangu.backend.security.CurrentUserService;
import com.thangu.backend.service.SellerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerDashboardServiceImpl implements SellerDashboardService {
    private final PropertyRepository propertyRepository;
    private final InquiryRepository inquiryRepository;
    private final FavoritePropertyRepository favoritePropertyRepository;
    private final CurrentUserService currentUserService;

    @Override
    public SellerDashboardResponse getDashboard() {
        User user = currentUserService.currentUser();
        return SellerDashboardResponse.builder()
                .activeProperties(propertyRepository.countBySellerAndListingStatus(user, "active"))
                .inActiveProperties(propertyRepository.countBySellerAndListingStatus(user, "inactive"))
                .totalFavorites(favoritePropertyRepository.countFavoriteBySeller(user))
                .totalInquiries(inquiryRepository.countBySeller(user))
                .recentInquiryResponse(inquiryRepository.findTop5BySellerOrderByCreatedAtDesc(user).stream().map(InquiryMapper::toResponse).toList())
                .build();
    }
}
