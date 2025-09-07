package com.fitness.userservice.controller;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/{UserId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String UserId){
        return ResponseEntity.ok(userService.getUserProfile(UserId));
    }

    @GetMapping("/{UserId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String UserId){
        return ResponseEntity.ok(userService.existsByUserId(UserId));
    }
}
