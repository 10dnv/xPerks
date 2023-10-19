package com.xperks.controller;

import com.xperks.dto.user.UserMainInfo;
import com.xperks.dto.user.UserModel;
import com.xperks.security.AuthUtil;
import com.xperks.service.user.UserServiceIF;
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
    public UserModel getUser() {
        return userService.getUser(AuthUtil.getAuthenticatedUserId());
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
