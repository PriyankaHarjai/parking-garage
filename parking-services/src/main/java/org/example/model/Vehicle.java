package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "vehicle")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String licencePlateNo;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
