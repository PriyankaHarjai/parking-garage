package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {

    private Long userId;
    private String userEmail;
    private String userPhoneNo;
    private boolean email;
    private boolean text;
    private String message;
}
