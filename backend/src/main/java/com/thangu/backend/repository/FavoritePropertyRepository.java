package com.thangu.backend.repository;

import com.thangu.backend.entity.FavoriteProperty;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritePropertyRepository extends JpaRepository<FavoriteProperty, Long> {
    boolean existsByUserAndProperty(User user, Property property); // Duplicate checks
    Optional<FavoriteProperty> findByUserAndProperty(User user, Property property); // Removing a favorite
    @EntityGraph(attributePaths = "property")
    Page<FavoriteProperty> findByUser(User user, Pageable pageable); //Listing a buyer's favorites
    Long countByUser(User user);
    List<FavoriteProperty> findTop5ByUserOrderByCreatedAtDesc(User user);
}
