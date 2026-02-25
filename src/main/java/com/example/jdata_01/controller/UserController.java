package com.example.jdata_01.controller;

import com.example.jdata_01.model.User;
import com.example.jdata_01.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService = null;

    @GetMapping("/{id}")
    public ResponseEntity<User> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
