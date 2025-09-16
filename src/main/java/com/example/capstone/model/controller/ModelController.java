package com.example.capstone.model.controller;

import com.example.capstone.model.dto.ModelCreateRequest;
import com.example.capstone.model.dto.ModelCreatedResponse;
import com.example.capstone.model.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModelCreatedResponse create(@Valid @RequestBody ModelCreateRequest req) {
        Long userId = currentUserId();
        return modelService.create(userId, req);
    }

    private Long currentUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()
                || auth.getPrincipal() instanceof String s && "anonymousUser".equals(s)) {
            throw new RuntimeException("Unauthenticated");
        }
        return Long.parseLong(auth.getName()); // <-- userId
    }

}
