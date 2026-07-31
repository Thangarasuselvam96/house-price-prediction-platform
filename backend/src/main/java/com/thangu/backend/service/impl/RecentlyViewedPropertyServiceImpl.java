package com.thangu.backend.service.impl;

import com.thangu.backend.dto.response.RecentlyViewedPropertyResponse;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.RecentlyViewedProperty;
import com.thangu.backend.entity.User;
import com.thangu.backend.repository.RecentlyViewedPropertyRepository;
import com.thangu.backend.security.CurrentUserService;
import com.thangu.backend.service.RecentlyViewedPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecentlyViewedPropertyServiceImpl implements RecentlyViewedPropertyService {
    private final RecentlyViewedPropertyRepository recentlyViewedPropertyRepository;
    private final CurrentUserService currentUserService;

    @Override
    public void recordRecentlyViewed(User user, Property property) {
        Optional<RecentlyViewedProperty> byUserAndProperty = recentlyViewedPropertyRepository.findByUserAndProperty(user, property);

        if(byUserAndProperty.isPresent()) {
            byUserAndProperty.get().setLastViewedAt(LocalDateTime.now());
        } else {
            RecentlyViewedProperty recentlyViewedProperty = new RecentlyViewedProperty();
            recentlyViewedProperty.setProperty(property);
            recentlyViewedProperty.setUser(user);
            recentlyViewedProperty.setLastViewedAt(LocalDateTime.now());

            recentlyViewedPropertyRepository.save(recentlyViewedProperty);
        }
    }

    @Override
    public List<RecentlyViewedPropertyResponse> recentlyViewedProperties() {
        User user = currentUserService.currentUser();

        if(user == null) {
            return Collections.emptyList();
        }

        return recentlyViewedPropertyRepository.findTop10ByUserOrderByLastViewedAtDesc(user)
                .stream().map(property -> new RecentlyViewedPropertyResponse(
                        property.getProperty().getId(),
                        property.getProperty().getTitle(),
                        property.getLastViewedAt()))
                .toList();
    }


}
