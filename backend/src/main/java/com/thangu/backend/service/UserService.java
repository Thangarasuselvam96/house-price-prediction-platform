package com.thangu.backend.service;

import com.thangu.backend.common.enums.RoleName;
import com.thangu.backend.dto.request.LoginRequest;
import com.thangu.backend.dto.request.RegisterRequest;
import com.thangu.backend.dto.response.LoginResponse;
import com.thangu.backend.dto.response.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest request, RoleName name);
    LoginResponse login(LoginRequest request);
}
