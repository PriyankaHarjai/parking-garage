package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "tickets")
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private String licencePlateNo;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double fee;
    @ManyToOne
    @JoinColumn(name = "slot_id")
    private ParkingSpot spot;

    public ParkingTicket(String licencePlateNo, LocalDateTime entryTime) {
        this.licencePlateNo = licencePlateNo;
        this.entryTime = entryTime;
    }
}
