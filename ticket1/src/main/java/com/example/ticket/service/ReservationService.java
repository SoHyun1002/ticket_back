package com.example.ticket.service;

import com.example.ticket.dto.Ticket;
import com.example.ticket.entity.Reservation;
import com.example.ticket.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;


    public Ticket getTicket(Long rId) {
        Optional<Reservation> reservation = reservationRepository.findById(rId);
        return reservation.map(res -> Ticket.builder()
                .tId(res.getRId())
                .rSpot(res.getRSpot())
                .uName(res.getUName())
                .pTitle(res.getPTitle())
                .pPlace(res.getPPlace())
                .pDate(res.getPDate())
                .build()).orElse(null);
    }

    public Ticket createTicket(Ticket ticket) {
        Reservation reservation = Reservation.builder()
                .uName(ticket.getUName())
                .pTitle(ticket.getPTitle())
                .pPlace(ticket.getPPlace())
                .pDate(ticket.getPDate() != null ? ticket.getPDate() : LocalDateTime.now()) // 기본값 설정
                .rSpot(ticket.getRSpot())
                .build();

        Reservation savedReservation = reservationRepository.save(reservation);

        return Ticket.builder()
                .tId(savedReservation.getRId()) // 생성된 ID 반환
                .uName(savedReservation.getUName())
                .pTitle(savedReservation.getPTitle())
                .pPlace(savedReservation.getPPlace())
                .pDate(savedReservation.getPDate())
                .rSpot(savedReservation.getRSpot())
                .build();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

}