package com.xperks.security;

import com.xperks.dto.user.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static int getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserModel) authentication.getPrincipal()).getId();
    }
}
