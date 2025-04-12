package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "parking_spot")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;
    private int slotNo;
    private int floorNo;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private boolean isOccupied;
    private boolean isReserved;
}
