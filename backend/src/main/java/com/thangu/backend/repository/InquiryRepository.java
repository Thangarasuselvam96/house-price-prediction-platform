package com.thangu.backend.repository;

import com.thangu.backend.entity.Inquiry;
import com.thangu.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Page<Inquiry> findByBuyer(User user, Pageable pageable);
    Page<Inquiry> findBySeller(User user, Pageable pageable);
}
