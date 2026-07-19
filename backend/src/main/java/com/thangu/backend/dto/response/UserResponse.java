package com.thangu.backend.dto.response;

import com.thangu.backend.common.enums.UserStatus;
import com.thangu.backend.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
    private UserStatus status;
    private LocalDateTime createdAt;
}
