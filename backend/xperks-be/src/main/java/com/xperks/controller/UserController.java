package com.xperks.controller;

import com.xperks.dto.user.UserMainInfo;
import com.xperks.dto.user.UserModel;
import com.xperks.service.user.UserServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/superior")
    @ResponseBody
    public boolean isSuperior() {
        return userService.isSuperior();
    }

    @PutMapping("/erd-address")
    public void changeErdAddress(@RequestParam String erdAddress) {
        userService.changeErdAddress(erdAddress);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<UserMainInfo> getUserList() {
        return userService.getUserList();
    }
}
