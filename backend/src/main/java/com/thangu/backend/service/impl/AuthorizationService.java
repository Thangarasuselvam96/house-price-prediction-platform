package com.thangu.backend.service.impl;

import com.thangu.backend.entity.Property;
import com.thangu.backend.entity.User;
import com.thangu.backend.security.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final CurrentUserService currentUserService;

    public void canModifyProperty(Property property) {

        User currentUser = currentUserService.currentUser();

        boolean isAdmin = currentUser.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return;
        }

        if (!property.getSeller().getId()
                .equals(currentUser.getId())) {

            throw new AccessDeniedException(
                    "Access denied");
        }
    }
}
