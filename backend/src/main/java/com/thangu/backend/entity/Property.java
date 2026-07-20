package com.thangu.backend.entity;

import com.thangu.backend.common.enums.ListingStatus;
import com.thangu.backend.common.enums.ListingType;
import com.thangu.backend.common.enums.PropertyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "property")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type", length = 50)
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    @Column(name = "listing_type", length = 20)
    private ListingType listingType;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
    @Column(name = "area_sqft")
    private Integer areaSqft;

    @Column(length = 100)
    private String city;
    @Column(length = 100)
    private String state;
    @Column(length = 10)
    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(name = "listing_status", length = 20)
    private ListingStatus listingStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Many properties belong to one seller.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;
}
