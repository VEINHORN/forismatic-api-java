package com.veinhorn.forismatic.proxy.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * This controller is used for user registration and authorization
 */
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserAuthenticationService authentication;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam(User.USERNAME) final String username,
                           @RequestParam(User.PASSWORD) final String password) {
        userService.save(User.builder().id(username).username(username).password(password).build());

        return authentication
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body) {
        return authentication
                .login(body.get(User.USERNAME), body.get(User.PASSWORD))
                .orElseThrow(() -> new RuntimeException("invalid login and/or username"));
    }
}
