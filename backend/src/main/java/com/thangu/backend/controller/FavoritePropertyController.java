package com.thangu.backend.controller;

import com.thangu.backend.dto.response.MessageResponse;
import com.thangu.backend.service.FavoritePropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor
public class FavoritePropertyController {
    private final FavoritePropertyService favoritePropertyService;

    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> addFavorite(@PathVariable Long propertyId) {
        favoritePropertyService.addFavorite(propertyId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                new MessageResponse("Property added to favorites")
        );
    }
}
