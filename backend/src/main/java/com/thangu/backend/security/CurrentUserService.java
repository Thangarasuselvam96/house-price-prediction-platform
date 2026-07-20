package com.thangu.backend.security;

import com.thangu.backend.entity.User;

public interface CurrentUserService {
    User currentUser();
}
