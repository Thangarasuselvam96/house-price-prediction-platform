package com.thangu.backend.service.impl;

import com.thangu.backend.common.enums.RoleName;
import com.thangu.backend.common.enums.UserStatus;
import com.thangu.backend.dto.request.RegisterRequest;
import com.thangu.backend.dto.response.UserResponse;
import com.thangu.backend.entity.Role;
import com.thangu.backend.exception.BadRequestException;
import com.thangu.backend.exception.ResourceNotFoundException;
import com.thangu.backend.mapper.UserMapper;
import com.thangu.backend.repository.RoleRepository;
import com.thangu.backend.repository.UserRepository;
import com.thangu.backend.service.UserService;

import java.util.Set;

public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private RoleRepository roleRepository;
    private UserMapper mapper;

    @Override
    public UserResponse register(RegisterRequest request) {
        if(repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        var role = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new ResourceNotFoundException("Internal server error"));

        var user = mapper.toEntity(request);
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(Set.of(role));
        repository.save(user);
        return mapper.toResponse(user);
    }
}
