package com.xperks.controller;

import com.xperks.dto.UserModel;
import com.xperks.service.UserServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceIF userService;

    @GetMapping("/{id}")
    @ResponseBody
    public UserModel getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping("/{id}/superior")
    public boolean isSuperior(@PathVariable int id) {
        return userService.isSuperior(id);
    }
}
