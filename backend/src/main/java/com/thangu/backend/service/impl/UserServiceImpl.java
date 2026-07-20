package com.thangu.backend.service.impl;

import com.thangu.backend.common.enums.RoleName;
import com.thangu.backend.common.enums.UserStatus;
import com.thangu.backend.dto.request.LoginRequest;
import com.thangu.backend.dto.request.RegisterRequest;
import com.thangu.backend.dto.response.LoginResponse;
import com.thangu.backend.dto.response.UserResponse;
import com.thangu.backend.entity.Role;
import com.thangu.backend.exception.BadRequestException;
import com.thangu.backend.exception.ResourceNotFoundException;
import com.thangu.backend.mapper.UserMapper;
import com.thangu.backend.repository.RoleRepository;
import com.thangu.backend.repository.UserRepository;
import com.thangu.backend.security.JWTService;
import com.thangu.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public UserResponse register(RegisterRequest request, RoleName name) {
        if(repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        var role = roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Internal server error"));

        var user = mapper.toEntity(request);
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(user);
        return mapper.toResponse(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        var accessToken = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()))
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }
}
