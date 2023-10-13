package com.xperks.controller;

import com.xperks.dto.UserModel;
import com.xperks.persistence.User;
import com.xperks.service.UserServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceIF userService;

    @GetMapping
    @ResponseBody
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserModel getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
}
