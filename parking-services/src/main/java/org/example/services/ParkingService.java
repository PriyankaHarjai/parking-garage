package org.example.services;

import org.example.model.*;
import org.example.repository.ParkingSpotRepository;
import org.example.repository.ParkingTicketRepository;
import org.example.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    private final ParkingSpotRepository spotRepository;
    private final ParkingTicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;
    RestTemplate restTemplate = new RestTemplate();

    public ParkingService(ParkingSpotRepository spotRepository, ParkingTicketRepository ticketRepository, VehicleRepository vehicleRepository) {
        this.spotRepository = spotRepository;
        this.ticketRepository = ticketRepository;
        this.vehicleRepository = vehicleRepository;
    }


    public void addSpot(ParkingSpot parkingSpot) {
        spotRepository.save(parkingSpot);
    }

    public List<ParkingSpot> fetchSpots() {
        return spotRepository.findAllByIsOccupiedFalseAndIsReservedFalse();
    }

    public ParkingTicket createTicketOnEntry(String licencePlateNo, VehicleType vehicleType) {
        ParkingSpot spot = spotRepository
                .findFirstByVehicleTypeAndIsOccupiedFalseAndIsReservedFalse(vehicleType)
                .orElseThrow(() -> new RuntimeException("No available slots for vehicle type: " + vehicleType));
        spot.setOccupied(true);
        spotRepository.save(spot);

        ParkingTicket parkingTicket = new ParkingTicket(licencePlateNo, LocalDateTime.now());
        parkingTicket.setSpot(spot);
        return ticketRepository.save(parkingTicket);


    }

    public String createTicketOnExit(Long ticketId) {
        ParkingTicket parkingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket id not found"));
        if (parkingTicket.getExitTime() != null) {
            throw new RuntimeException("Ticket already closed");
        }
        parkingTicket.setExitTime(LocalDateTime.now());

        Duration duration = Duration.between(parkingTicket.getEntryTime(), parkingTicket.getExitTime());
        //minimum 1 hr amount would be charged
        long hours = Math.max(1, duration.toHours());
        double fee = hours * getPrice(parkingTicket.getSpot().getVehicleType());
        parkingTicket.setFee(fee);
        ParkingSpot parkingSpot = parkingTicket.getSpot();
        parkingSpot.setOccupied(false);
        parkingTicket.setSpot(parkingSpot);

        ticketRepository.save(parkingTicket);

        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8082/api/payment")
                .queryParam("ticketId", parkingTicket.getTicketId())
                .queryParam("amount", parkingTicket.getFee())
                .build()
                .toUri();

        // Send POST request with query parameters and body
        Payment payment = restTemplate.postForObject(uri, null, Payment.class);
        if (payment.getStatus() != null && payment.getStatus().equalsIgnoreCase("Completed")) {
            //send notification about payment success.
            //licencePlate no-  user id and email phone no User object
            // and message
            String licencePlateNo = parkingTicket.getLicencePlateNo();
            Vehicle vehicle = vehicleRepository.findByLicencePlateNo(licencePlateNo)
                    .orElseThrow(() -> new RuntimeException("Not valid licence plate no"));
            User user = vehicle.getUser();
            String message = "Payment of " + payment.getAmountPaid() + " has been made successfully for " + user.getName();
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setUserId(user.getUserId());
            notificationDTO.setUserEmail(user.getEmail());
            notificationDTO.setUserPhoneNo(user.getPhoneNo());
            notificationDTO.setEmail(true);
            notificationDTO.setMessage(message);

            URI uri1 = UriComponentsBuilder
                    .fromHttpUrl("http://localhost:8083/api/notification/send")
                    .build()
                    .toUri();
            // Send POST request with query parameters and body
            return restTemplate.postForObject(uri1, notificationDTO, String.class);

        } else {
            throw new RuntimeException("Payment not successfully done.");
        }
    }

    private long getPrice(VehicleType vehicleType) {
        if (vehicleType.name().equalsIgnoreCase("SUV")) {
            return 20;
        } else if (vehicleType.name().equalsIgnoreCase("SEDAN")) {
            return 10;
        } else if (vehicleType.name().equalsIgnoreCase("Truck")) {
            return 30;
        } else {
            return 15;
        }
    }
}
