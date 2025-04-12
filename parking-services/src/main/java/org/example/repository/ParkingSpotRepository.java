package org.example.repository;

import org.example.model.ParkingSpot;
import org.example.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findFirstByVehicleTypeAndIsOccupiedFalseAndIsReservedFalse(VehicleType vehicleType);

    List<ParkingSpot> findAllByIsOccupiedFalseAndIsReservedFalse();
}
