package com.thangu.backend.repository;

import com.thangu.backend.entity.FavoriteProperty;
import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FavoritePropertyRepositoryTest {
    @Autowired
    private FavoritePropertyRepository favoritePropertyRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user1;
    private User user2;
    private Property property1;
    private Property property2;

    @BeforeEach
    void setUp() {
        user1 = entityManager.persist(User.builder()
                .email("user@test.com")
                .build());

        user2 = entityManager.persist(User.builder()
                .email("another@test.com")
                .build());

        property1 = entityManager.persist(Property.builder()
                .title("Villa")
                .build());

        property2 = entityManager.persist(Property.builder()
                .title("Apartment")
                .build());

        entityManager.flush();
    }

    @Test
    void shouldReturnTrueWhenFavoriteExists() {
        FavoriteProperty favoriteProperty = FavoriteProperty.builder()
                .user(user1)
                .property(property1)
                .build();
        entityManager.persist(favoriteProperty);
        entityManager.flush();

        boolean exists = favoritePropertyRepository.existsByUserAndProperty(user1, property1);

        assertTrue(exists);
    }
}