package com.thangu.backend.repository;

import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.RecentlyViewedProperty;
import com.thangu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecentlyViewedPropertyRepository extends JpaRepository<RecentlyViewedProperty, Long> {
    Optional<RecentlyViewedProperty> findByUserAndProperty(User user, Property property);
    List<RecentlyViewedProperty> findTop10ByUserOrderByLastViewedAtDesc(User user);
}
