package com.thangu.backend.service.impl;

import com.thangu.backend.dto.request.PropertySearchRequest;
import com.thangu.backend.dto.response.PageResponse;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.dto.response.SellerDashboardResponse;
import com.thangu.backend.entity.User;
import com.thangu.backend.mapper.InquiryMapper;
import com.thangu.backend.mapper.PageMapper;
import com.thangu.backend.mapper.PropertyMapper;
import com.thangu.backend.repository.FavoritePropertyRepository;
import com.thangu.backend.repository.InquiryRepository;
import com.thangu.backend.repository.PropertyRepository;
import com.thangu.backend.security.CurrentUserService;
import com.thangu.backend.service.SellerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerDashboardServiceImpl implements SellerDashboardService {
    private final PropertyRepository propertyRepository;
    private final InquiryRepository inquiryRepository;
    private final FavoritePropertyRepository favoritePropertyRepository;
    private final CurrentUserService currentUserService;
    private final PropertyMapper mapper;

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

    @Override
    public PageResponse<PropertyResponse> getProperties(PropertySearchRequest request) {
        User user = currentUserService.currentUser();
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy()));
        return PageMapper.from(propertyRepository.findBySeller(user, pageRequest).map(mapper::toResponse));
    }
}
