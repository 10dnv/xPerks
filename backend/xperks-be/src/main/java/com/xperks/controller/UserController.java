package com.xperks.controller;

import com.xperks.dto.UserMainInfo;
import com.xperks.dto.UserModel;
import com.xperks.service.UserServiceIF;
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

    @GetMapping("/{id}/superior")
    @ResponseBody
    public boolean isSuperior(@PathVariable int id) {
        return userService.isSuperior(id);
    }

    @PutMapping("/{id}/erd-address")
    public void changeErdAddress(@PathVariable int id, @RequestParam String erdAddress) {
        userService.changeErdAddress(id, erdAddress);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<UserMainInfo> getUserList(String searchValue) {
        return userService.getUserList(searchValue);
    }
}
