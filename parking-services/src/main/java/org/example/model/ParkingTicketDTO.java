package org.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ParkingTicketDTO {
    private Long ticketId;
    private String licencePlateNo;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double fee;
    private int spotNo;
    private int floorNo;
}
