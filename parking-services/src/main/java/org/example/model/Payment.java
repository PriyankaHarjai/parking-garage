package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class Payment {
    private Long paymentId;
    private Long ticketId;
    private Double amountPaid;
    private LocalDateTime paymentTime;
    private String status;
}
