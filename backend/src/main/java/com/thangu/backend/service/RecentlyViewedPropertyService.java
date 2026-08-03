package com.thangu.backend.service;

import com.thangu.backend.dto.response.RecentlyViewedPropertyResponse;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;

import java.util.List;

public interface RecentlyViewedPropertyService {
    void recordRecentlyViewed(User user, Property property);
    List<RecentlyViewedPropertyResponse> recentlyViewedProperties();
}
