package org.example.repository;

import org.example.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
}
