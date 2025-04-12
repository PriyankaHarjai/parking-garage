package org.example.controllers;

import org.example.mappers.ParkingDTOMappers;
import org.example.model.*;
import org.example.services.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

    private final ParkingService parkingService;
    private final ParkingDTOMappers mappers;

    public ParkingController(ParkingService parkingService, ParkingDTOMappers mappers) {
        this.parkingService = parkingService;
        this.mappers = mappers;
    }

    @PostMapping("/add-spot")
    public void addParkingSpot(@RequestBody ParkingSpotDTO parkingSpotDTO) {
        parkingService.addSpot(new ParkingSpot(null, parkingSpotDTO.getSlotNo(), parkingSpotDTO.getFloorNo(), parkingSpotDTO.getVehicleType(), parkingSpotDTO.isOccupied(), parkingSpotDTO.isReserved()));
    }

    @GetMapping("/availableSpots")
    public List<ParkingSpotDTO> availableSpots() {
        List<ParkingSpot> parkingSpots = parkingService.fetchSpots();
        return parkingSpots.stream()
                .map(mappers::convertToDTO)
                .toList();
    }

    @PostMapping("/ticket/entry")
    public ParkingTicketDTO enterGate(@RequestParam String licencePlateNo, @RequestParam VehicleType VehicleType) {
        ParkingTicket parkingTicket = parkingService.createTicketOnEntry(licencePlateNo, VehicleType);
        return mappers.convertParkingTicketToDTO(parkingTicket);
    }

    @PutMapping("/ticket/exit/{ticketId}")
    public ResponseEntity<String> exitGate(@PathVariable Long ticketId) {
        return ResponseEntity.ok(parkingService.createTicketOnExit(ticketId));
    }
}
