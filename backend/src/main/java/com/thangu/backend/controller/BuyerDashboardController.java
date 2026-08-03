package com.thangu.backend.controller;

import com.thangu.backend.dto.response.BuyerDashboardResponse;
import com.thangu.backend.dto.response.RecentlyViewedPropertyResponse;
import com.thangu.backend.service.BuyerDashboardService;
import com.thangu.backend.service.RecentlyViewedPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buyer")
@RequiredArgsConstructor
public class BuyerDashboardController {
    private final BuyerDashboardService buyerDashboardService;
    private final RecentlyViewedPropertyService recentlyViewedPropertyService;

    @GetMapping("/dashboard")
    public ResponseEntity<BuyerDashboardResponse> dashboard() {
        return ResponseEntity.ok(buyerDashboardService.getDashboard());
    }

    @GetMapping("/recently-viewed")
    public ResponseEntity<List<RecentlyViewedPropertyResponse>> recentlyViewedProperties() {
        return ResponseEntity.ok(recentlyViewedPropertyService.recentlyViewedProperties());
    }
}
