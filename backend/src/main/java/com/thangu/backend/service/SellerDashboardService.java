package com.thangu.backend.service;

import com.thangu.backend.dto.request.PropertySearchRequest;
import com.thangu.backend.dto.response.PageResponse;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.dto.response.SellerDashboardResponse;

public interface SellerDashboardService {
    SellerDashboardResponse getDashboard();
    PageResponse<PropertyResponse> getProperties(PropertySearchRequest request);
}
