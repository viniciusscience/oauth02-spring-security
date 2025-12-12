package com.security.authservice.controller.user;

import com.security.authservice.dto.UserDto;
import com.security.authservice.dto.UserRegisterInput;
import com.security.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRegisterInput userRegisterInput) {
        userService.createUser(userRegisterInput);
        return ResponseEntity.ok().build();
    }
}
