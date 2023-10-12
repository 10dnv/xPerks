package com.xperks.security.auth;

import com.xperks.security.config.JwtService;
import com.xperks.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginServiceIF {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getPassword()));
        var user = userService.findUserByEmailAddress(request.getUserEmail());
        Objects.requireNonNull(user);
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder().token(jwtToken).firstName(user.getFirstName()).build();
    }
}
