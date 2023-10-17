package com.xperks.controller;

import com.xperks.dto.notification.NotificationModel;
import com.xperks.service.notification.NotificationServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServiceIF notificationService;

    @GetMapping
    @ResponseBody
    public List<NotificationModel> getNotifications() {
        return notificationService.getNotifications();
    }
}
