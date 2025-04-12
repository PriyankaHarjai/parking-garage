package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ParkingSpotDTO {

    private int slotNo;
    private int floorNo;
    private VehicleType vehicleType;
    private boolean isOccupied;
    private boolean isReserved;
}
