package com.thangu.backend.service.impl;

import com.thangu.backend.dto.response.BuyerDashboardResponse;
import com.thangu.backend.entity.FavoriteProperty;
import com.thangu.backend.entity.Inquiry;
import com.thangu.backend.entity.User;
import com.thangu.backend.mapper.FavoritePropertyMapper;
import com.thangu.backend.mapper.InquiryMapper;
import com.thangu.backend.repository.FavoritePropertyRepository;
import com.thangu.backend.repository.InquiryRepository;
import com.thangu.backend.security.CurrentUserService;
import com.thangu.backend.service.BuyerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerDashboardServiceImpl implements BuyerDashboardService {
    private final FavoritePropertyRepository favoritePropertyRepository;
    private final InquiryRepository inquiryRepository;
    private final CurrentUserService currentUserService;

    @Override
    public BuyerDashboardResponse getDashboard() {
        User user = currentUserService.currentUser();
        Long favCount = favoritePropertyRepository.countByUser(user);
        Long inquiryCount = inquiryRepository.countByBuyer(user);
        var recentFav = favoritePropertyRepository.findTop5ByUserOrderByCreatedAtDesc(user)
                .stream().map(FavoritePropertyMapper::toResponse).toList();
        var recentInquiry = inquiryRepository.findTop5ByBuyerOrderByCreatedAtDesc(user)
                .stream().map(InquiryMapper::toResponse).toList();

        return BuyerDashboardResponse.builder()
                .favoriteCount(favCount)
                .inquiryCount(inquiryCount)
                .recentFavorites(recentFav)
                .recentInquiries(recentInquiry)
                .build();
    }
}
