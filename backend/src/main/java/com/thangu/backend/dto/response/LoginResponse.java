package com.thangu.backend.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> roles;
    private String message;
}
