package com.thangu.backend.controller;

import com.thangu.backend.dto.request.InquiryRequest;
import com.thangu.backend.dto.response.MessageResponse;
import com.thangu.backend.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @PostMapping("/{propertyId}/inquiry")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> createInquiry(@PathVariable Long propertyId, @RequestBody InquiryRequest inquiryRequest) {
        inquiryService.createInquiry(propertyId, inquiryRequest);
        return ResponseEntity.ok(
                new MessageResponse("Inquiry sent successfully")
        );
    }
}
