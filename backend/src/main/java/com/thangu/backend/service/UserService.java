package com.thangu.backend.service;

import com.thangu.backend.dto.request.RegisterRequest;
import com.thangu.backend.dto.response.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest request);
}
