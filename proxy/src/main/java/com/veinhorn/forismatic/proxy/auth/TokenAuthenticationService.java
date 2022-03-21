package com.veinhorn.forismatic.proxy.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
public class TokenAuthenticationService implements UserAuthenticationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    public Optional<String> login(String username, String password) {
        return userService
                .findByUsername(username)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> tokenService.newToken(Collections.singletonMap("username", username)));
    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional
                .of(tokenService.verify(token))
                .map(map -> map.get("username"))
                .flatMap(userService::findByUsername);
    }

    @Override
    public void logout(User user) {}
}
