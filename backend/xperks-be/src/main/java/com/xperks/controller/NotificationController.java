package com.xperks.controller;

import com.xperks.dto.notification.NotificationModel;
import com.xperks.service.notification.NotificationServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/read/{id}")
    public void readNotification(@PathVariable int id) {
        notificationService.readNotification(id);
    }
}
