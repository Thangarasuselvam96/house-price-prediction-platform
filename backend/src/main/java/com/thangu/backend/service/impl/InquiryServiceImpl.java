package com.thangu.backend.service.impl;

import com.thangu.backend.dto.request.InquiryRequest;
import com.thangu.backend.entity.Inquiry;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;
import com.thangu.backend.exception.BusinessException;
import com.thangu.backend.exception.ResourceNotFoundException;
import com.thangu.backend.repository.InquiryRepository;
import com.thangu.backend.repository.PropertyRepository;
import com.thangu.backend.security.CurrentUserService;
import com.thangu.backend.service.InquiryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;
    private final PropertyRepository propertyRepository;
    private final CurrentUserService currentUserService;

    @Transactional
    @Override
    public void createInquiry(Long propertyId, InquiryRequest inquiryRequest) {
        User user = currentUserService.currentUser();

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        if(property.getSeller().getId().equals(user.getId())) {
            throw new BusinessException("You cannot inquiry about your own property");
        }

        Inquiry inquiry = Inquiry.builder()
                .buyer(user)
                .seller(property.getSeller())
                .property(property)
                .message(inquiryRequest.getMessage())
                .status("New")
                .build();

        inquiryRepository.save(inquiry);
    }
}
