package com.thangu.backend.service;

import com.thangu.backend.dto.response.FavoritePropertyResponse;
import com.thangu.backend.dto.response.PageResponse;
import org.springframework.data.domain.Page;

public interface FavoritePropertyService {
    void addFavorite(Long propertyId);
    PageResponse<FavoritePropertyResponse> getFavorites(int page, int size, String sortBy, String direction);
    void removeFavorite(Long propertyId);
}
