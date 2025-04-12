package org.example.controllers;

import org.example.model.NotificationDTO;
import org.example.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody NotificationDTO notificationDTO) {
        notificationService.send(notificationDTO);
        return ResponseEntity.ok("Notification has been successfully sent");
    }
}
