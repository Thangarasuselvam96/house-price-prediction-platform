package com.thangu.backend.controller;

import com.thangu.backend.dto.request.PropertySearchRequest;
import com.thangu.backend.dto.response.PageResponse;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.dto.response.SellerDashboardResponse;
import com.thangu.backend.service.SellerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerDashboardController {
    private final SellerDashboardService sellerDashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<SellerDashboardResponse> getDashboard() {
        return ResponseEntity.ok(sellerDashboardService.getDashboard());
    }

    @GetMapping("/properties")
    public ResponseEntity<PageResponse<PropertyResponse>> getProperties(@ModelAttribute PropertySearchRequest request) {
        return ResponseEntity.ok(sellerDashboardService.getProperties(request));
    }
}
