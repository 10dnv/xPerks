package com.xperks.security.auth;

import com.xperks.dto.auth.LoginRequest;
import com.xperks.dto.auth.LoginResponse;

public interface LoginServiceIF {
    LoginResponse login(LoginRequest request);
}
