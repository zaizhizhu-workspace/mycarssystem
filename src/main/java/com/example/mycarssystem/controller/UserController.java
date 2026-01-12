package com.example.mycarssystem.controller;

import com.example.mycarssystem.entity.User;
import com.example.mycarssystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getByIdCard/{idCard}")
    public ResponseEntity<?> getByIdCard(@PathVariable String idCard) {
        return userService.getByIdCard(idCard);
    }

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam String idCard) {
        return userService.deleteUser(idCard);
    }

    @PostMapping("/update")
    public String update(@RequestBody User user) {
        return userService.updateUser(user);
    }
}