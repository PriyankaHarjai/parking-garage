package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "vehicle")
@Data
@Getter
@Setter
public class Vehicle {
    @Id
    private Long vehicleId;
    private String licencePlate;
    private VehicleType vehicleType;
    private Long userId;
}
