package com.thangu.backend.repository;

import com.thangu.backend.entity.FavoriteProperty;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritePropertyRepository extends JpaRepository<FavoriteProperty, Long> {
    boolean existsByUserAndProperty(User user, Property property); // Duplicate checks
    Optional<FavoriteProperty> findByUserAndProperty(User user, Property property); // Removing a favorite
    List<FavoriteProperty> findByUser(User user); //Listing a buyer's favorites
}
