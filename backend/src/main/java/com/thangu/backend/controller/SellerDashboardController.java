package com.thangu.backend.controller;

import com.thangu.backend.dto.response.SellerDashboardResponse;
import com.thangu.backend.service.SellerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerDashboardController {
    private final SellerDashboardService sellerDashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<SellerDashboardResponse> getDashboard() {
        return ResponseEntity.ok(sellerDashboardService.getDashboard());
    }
}
