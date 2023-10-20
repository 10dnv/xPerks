package com.xperks.controller;

import com.xperks.dto.user.UserMainInfo;
import com.xperks.dto.user.UserModel;
import com.xperks.security.AuthUtil;
import com.xperks.service.user.UserServiceIF;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PostMapping("/redeem")
    public void redeemEgld(@RequestParam int pts, @RequestParam @Positive @Digits(integer = 10, fraction = 3) BigDecimal egldAmmount) throws Exception {
        userService.redeemEgld(pts, egldAmmount);
    }
}
