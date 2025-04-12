package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.VehicleType;

@Getter
@Setter
public class VehicleDTO {
    private String licencePlate;
    private VehicleType vehicleType;
}
