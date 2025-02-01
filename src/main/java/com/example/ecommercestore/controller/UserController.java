package com.example.ecommercestore.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user-info")
public class UserController {

    @GetMapping
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OAuth2User user) {
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("authenticated", true);
            response.put("email", user.getAttribute("email"));
        } else {
            response.put("authenticated", false);
        }
        return response;
    }

}
