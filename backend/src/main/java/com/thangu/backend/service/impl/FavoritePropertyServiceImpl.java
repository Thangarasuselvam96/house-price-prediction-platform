package com.thangu.backend.service.impl;

import com.thangu.backend.dto.response.FavoritePropertyResponse;
import com.thangu.backend.dto.response.PageResponse;
import com.thangu.backend.entity.FavoriteProperty;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;
import com.thangu.backend.exception.BusinessException;
import com.thangu.backend.exception.ResourceNotFoundException;
import com.thangu.backend.mapper.PageMapper;
import com.thangu.backend.repository.FavoritePropertyRepository;
import com.thangu.backend.repository.PropertyRepository;
import com.thangu.backend.security.CurrentUserService;
import com.thangu.backend.service.FavoritePropertyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritePropertyServiceImpl implements FavoritePropertyService {
    private final FavoritePropertyRepository favoritePropertyRepository;
    private final PropertyRepository propertyRepository;
    private final CurrentUserService currentUserService;

    @Override
    @Transactional // Save Favorite -> Exception -> Rollback
    @PreAuthorize("hasRole('USER')")
    public void addFavorite(Long propertyId) {
        User user = currentUserService.currentUser();
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        boolean exists = favoritePropertyRepository.existsByUserAndProperty(user, property);

        if(exists) {
            throw new BusinessException("Property already added to favorites");
        }

        FavoriteProperty favoriteProperty = FavoriteProperty.builder()
                .user(user)
                .property(property)
                .build();
        favoritePropertyRepository.save(favoriteProperty);
    }

    @Override
    public PageResponse<FavoritePropertyResponse> getFavorites(int page, int size, String sortBy, String direction) {
        User user = currentUserService.currentUser();
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<FavoritePropertyResponse> pages = favoritePropertyRepository.findByUser(user, pageable)
                .map(property -> FavoritePropertyResponse.builder()
                        .propertyId(property.getId())
                        .city(property.getProperty().getCity())
                        .price(property.getProperty().getPrice())
                        .title(property.getProperty().getTitle())
                        .propertyType(property.getProperty().getPropertyType())
                        .favoriteDate(property.getCreatedAt())
                        .build());
        return PageMapper.from(pages);
    }

    @Override
    public void removeFavorite(Long propertyId) {
        User currentUser = currentUserService.currentUser();
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        FavoriteProperty favoriteProperty = favoritePropertyRepository.findByUserAndProperty(currentUser, property)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found"));
        favoritePropertyRepository.delete(favoriteProperty);
    }
}
