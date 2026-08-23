package com.thangu.backend.controller;

import com.thangu.backend.dto.request.PropertySearchRequest;
import com.thangu.backend.dto.request.PropertyStatusUpdateRequest;
import com.thangu.backend.dto.response.PageResponse;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.dto.response.SellerDashboardResponse;
import com.thangu.backend.service.PropertyService;
import com.thangu.backend.service.SellerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerDashboardController {
    private final SellerDashboardService sellerDashboardService;
    private final PropertyService propertyService;

    @GetMapping("/dashboard")
    public ResponseEntity<SellerDashboardResponse> getDashboard() {
        return ResponseEntity.ok(sellerDashboardService.getDashboard());
    }

    @GetMapping("/properties")
    public ResponseEntity<PageResponse<PropertyResponse>> getProperties(@ModelAttribute PropertySearchRequest request) {
        return ResponseEntity.ok(sellerDashboardService.getProperties(request));
    }

    @PatchMapping("/properties/{propertyId}/status")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<PropertyResponse> updateStatus(
            @PathVariable Long propertyId,
            @RequestBody PropertyStatusUpdateRequest request
            ) {
        return ResponseEntity.ok(propertyService.updateStatus(propertyId, request));
    }
}
