package com.thangu.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "recently_viewed_properties",
        uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "user_id",
                        "property_id"
                }
        )
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecentlyViewedProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "last_viewed_at")
    private LocalDateTime lastViewedAt;
}
