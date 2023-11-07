package com.example.resourceserver.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemoService {

    public Map<String, String> greet(Jwt identity) {
        return Map.of("message", "Welcome, %s!".formatted(identity.getSubject()));
    }
}
