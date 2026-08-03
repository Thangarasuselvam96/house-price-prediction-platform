package com.thangu.backend.mapper;

import com.thangu.backend.dto.response.FavoritePropertyResponse;
import com.thangu.backend.dto.response.InquiryResponse;
import com.thangu.backend.entity.FavoriteProperty;
import com.thangu.backend.entity.Inquiry;

public class InquiryMapper {
    private InquiryMapper() {}

    public static InquiryResponse toResponse(Inquiry inquiry) {
        return InquiryResponse.builder()
                .propertyId(inquiry.getProperty().getId())
                .status(inquiry.getStatus())
                .message(inquiry.getMessage())
                .build();
    }
}
