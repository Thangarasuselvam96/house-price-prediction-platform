package com.thangu.backend.mapper;

import com.thangu.backend.dto.request.RegisterRequest;
import com.thangu.backend.dto.response.UserResponse;
import com.thangu.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(RegisterRequest request);
    UserResponse toResponse(User user);
}
