package org.example.mappers;

import org.example.model.ParkingSpot;
import org.example.model.ParkingSpotDTO;
import org.example.model.ParkingTicket;
import org.example.model.ParkingTicketDTO;
import org.springframework.stereotype.Component;

@Component
public class ParkingDTOMappers {

    public ParkingSpotDTO convertToDTO(ParkingSpot spot) {
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO();
        parkingSpotDTO.setSlotNo(spot.getSlotNo());
        parkingSpotDTO.setFloorNo(spot.getFloorNo());
        parkingSpotDTO.setVehicleType(spot.getVehicleType());
        parkingSpotDTO.setReserved(spot.isReserved());
        parkingSpotDTO.setOccupied(spot.isOccupied());
        return parkingSpotDTO;
    }

    public ParkingTicketDTO convertParkingTicketToDTO(ParkingTicket parkingTicket) {
        ParkingTicketDTO dto = new ParkingTicketDTO();
        dto.setTicketId(parkingTicket.getTicketId());
        dto.setEntryTime(parkingTicket.getEntryTime());
        dto.setLicencePlateNo(parkingTicket.getLicencePlateNo());
        dto.setSpotNo(parkingTicket.getSpot().getSlotNo());
        dto.setFloorNo(parkingTicket.getSpot().getFloorNo());
        return dto;
    }

    public ParkingTicketDTO convertExitTicketDTO(ParkingTicket parkingTicket) {
        ParkingTicketDTO parkingTicketDTO = new ParkingTicketDTO();
        parkingTicketDTO.setFee(parkingTicketDTO.getFee());
        parkingTicketDTO.setExitTime(parkingTicketDTO.getExitTime());
        parkingTicketDTO.setLicencePlateNo(parkingTicketDTO.getLicencePlateNo());
        parkingTicketDTO.setEntryTime(parkingTicket.getEntryTime());

        return parkingTicketDTO;
    }
}
