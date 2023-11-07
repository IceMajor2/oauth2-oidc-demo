package com.example.resourceserver.controller;

import com.example.resourceserver.service.DemoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/welcome")
    @PreAuthorize("hasAuthority('SCOPE_user.read')")
    public Map<String, String> authorized(@AuthenticationPrincipal Jwt identity) {
        return demoService.greet(identity);
    }
}
