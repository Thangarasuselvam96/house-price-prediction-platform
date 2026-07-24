package com.thangu.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "favorite_properties",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_property",
                        columnNames = {"user_id", "property_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Lazy loading avoids unnecessary queries.
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
