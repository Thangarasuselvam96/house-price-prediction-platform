package com.thangu.backend.service.impl;

import com.thangu.backend.entity.FavoriteProperty;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;
import com.thangu.backend.exception.BusinessException;
import com.thangu.backend.exception.ResourceNotFoundException;
import com.thangu.backend.repository.FavoritePropertyRepository;
import com.thangu.backend.repository.PropertyRepository;
import com.thangu.backend.security.CurrentUserService;
import com.thangu.backend.service.FavoritePropertyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
}
