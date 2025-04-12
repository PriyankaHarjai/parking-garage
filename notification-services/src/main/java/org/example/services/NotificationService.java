package org.example.services;

import org.example.model.Notification;
import org.example.model.NotificationDTO;
import org.example.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public void send(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setUserId(notificationDTO.getUserId());
        notification.setMessage(notificationDTO.getMessage());
        notification.setSentTime(LocalDateTime.now());
        notification.setStatus("Sent");

       /* if (notificationDTO.isEmail()) {
            //email sending logic
        } else if (notificationDTO.isText()) {
            //text sending logic
        }
        */

        notificationRepository.save(notification);
    }
}
