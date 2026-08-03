package com.thangu.backend.controller;

import com.thangu.backend.dto.response.FavoritePropertyResponse;
import com.thangu.backend.dto.response.MessageResponse;
import com.thangu.backend.dto.response.PageResponse;
import com.thangu.backend.service.FavoritePropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<PageResponse<FavoritePropertyResponse>> getFavorites(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy, @RequestParam(name = "direction", defaultValue = "desc") String direction) {
        return ResponseEntity.ok(
                favoritePropertyService.getFavorites(page, size, sortBy, direction)
        );
    }

    @DeleteMapping("/propertyId")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long propertyId) {
        favoritePropertyService.removeFavorite(propertyId);
        return ResponseEntity.noContent().build();
    }
}
