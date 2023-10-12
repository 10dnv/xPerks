package com.xperks.controller;

import com.xperks.dto.UserModel;
import com.xperks.persistence.User;
import com.xperks.service.UserServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceIF userService;

    @GetMapping("/user")
    @ResponseBody
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserModel getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
}
