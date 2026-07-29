package com.thangu.backend.controller;

import com.thangu.backend.dto.response.BuyerDashboardResponse;
import com.thangu.backend.service.BuyerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buyer")
@RequiredArgsConstructor
public class BuyerDashboardController {
    private final BuyerDashboardService buyerDashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<BuyerDashboardResponse> dashboard() {
        return ResponseEntity.ok(buyerDashboardService.getDashboard());
    }
}
