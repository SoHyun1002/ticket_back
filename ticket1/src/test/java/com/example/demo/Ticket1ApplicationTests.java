package com.example.demo;

import com.example.ticket.dto.Ticket;
import com.example.ticket.entity.Reservation;
import com.example.ticket.repository.ReservationRepository;
import com.example.ticket.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Ticket1ApplicationTests {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("예약 ID로 Ticket 조회 테스트")
    void testGetTicket() {
        // Given (테스트용 Reservation 데이터 준비)
        Long rId = 1L;
        Reservation reservation = new Reservation();
        reservation.setRId(rId);
        reservation.setUName("홍길동");
        reservation.setRSpot("A1");
        reservation.setPDate(LocalDateTime.of(2025, 4, 10,  10, 10));
        reservation.setPTitle("뮤지컬 오페라의 유령");
        reservation.setPPlace("서울 예술의 전당");

        // Mock 동작 정의
        when(reservationRepository.findById(rId)).thenReturn(Optional.of(reservation));

        // When (서비스 호출)
        Ticket ticket = reservationService.getTicket(rId);

        // Then (검증)
        assertNotNull(ticket);
        assertEquals(reservation.getRId(), ticket.getTId());
        assertEquals(reservation.getUName(), ticket.getUName());
        assertEquals(reservation.getRSpot(), ticket.getRSpot());
        assertEquals(reservation.getPDate(), ticket.getPDate());
        assertEquals(reservation.getPTitle(), ticket.getPTitle());
        assertEquals(reservation.getPPlace(), ticket.getPPlace());

        // findById가 한 번만 호출되었는지 확인
        verify(reservationRepository, times(1)).findById(rId);
    }

    @Test
    @DisplayName("존재하지 않는 예약 ID 조회 시 null 반환")
    void testGetTicket_NotFound() {
        // Given
        Long rId = 999L;
        when(reservationRepository.findById(rId)).thenReturn(Optional.empty());

        // When
        Ticket ticket = reservationService.getTicket(rId);

        // Then
        assertNull(ticket);
        verify(reservationRepository, times(1)).findById(rId);
    }
}
