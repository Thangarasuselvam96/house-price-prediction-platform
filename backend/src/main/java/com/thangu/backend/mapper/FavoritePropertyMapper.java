package com.thangu.backend.mapper;

import com.thangu.backend.dto.response.FavoritePropertyResponse;
import com.thangu.backend.entity.FavoriteProperty;

public class FavoritePropertyMapper {
    private FavoritePropertyMapper() {}

    public static FavoritePropertyResponse toResponse(FavoriteProperty favoriteProperty) {
        return FavoritePropertyResponse.builder()
                .title(favoriteProperty.getProperty().getTitle())
                .price(favoriteProperty.getProperty().getPrice())
                .city(favoriteProperty.getProperty().getCity())
                .favoriteDate(favoriteProperty.getCreatedAt())
                .propertyType(favoriteProperty.getProperty().getPropertyType())
                .propertyId(favoriteProperty.getProperty().getId())
                .build();
    }
}
