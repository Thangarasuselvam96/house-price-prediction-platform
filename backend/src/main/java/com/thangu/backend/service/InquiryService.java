package com.thangu.backend.service;

import com.thangu.backend.dto.request.InquiryRequest;

public interface InquiryService {
    void createInquiry(Long propertyId, InquiryRequest inquiryRequest);
}
