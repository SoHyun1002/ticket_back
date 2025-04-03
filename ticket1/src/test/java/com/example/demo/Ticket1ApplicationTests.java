package com.example.demo;

import com.example.ticket.dto.Ticket;
import com.example.ticket.entity.Reservation;
import com.example.ticket.repository.ReservationRepository;
import com.example.ticket.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("✅ 예약 ID로 Ticket 조회 - 성공")
    void testGetTicket_Success() {
        // Given (테스트용 예약 데이터 설정)
        Long rId = 1L;
        Reservation reservation = new Reservation();
        reservation.setRId(rId);
        reservation.setUName("홍길동");
        reservation.setRSpot("A1");
        reservation.setPDate(LocalDateTime.of(2025, 4, 10, 10, 10));
        reservation.setPTitle("title");
        reservation.setPPlace("서울 예술의 전당");

        // Mock 동작 정의
        when(reservationRepository.findById(rId)).thenReturn(Optional.of(reservation));

        // When (서비스 호출)
        Ticket ticket = reservationService.getTicket(rId);

        // Then (결과 검증)
        assertNotNull(ticket, "티켓은 null이면 안 됨");
        assertEquals(reservation.getRId(), ticket.getTId(), "티켓 ID가 일치해야 함");
        assertEquals(reservation.getUName(), ticket.getUName(), "예약자 이름이 일치해야 함");
        assertEquals(reservation.getRSpot(), ticket.getRSpot(), "좌석 정보가 일치해야 함");
        assertEquals(reservation.getPDate(), ticket.getPDate(), "공연 날짜가 일치해야 함");
        assertEquals(reservation.getPTitle(), ticket.getPTitle(), "공연명이 일치해야 함");
        assertEquals(reservation.getPPlace(), ticket.getPPlace(), "공연장이 일치해야 함");

        // findById가 한 번만 호출되었는지 확인
        verify(reservationRepository, times(1)).findById(rId);
    }

    @Test
    @DisplayName("❌ 존재하지 않는 예약 ID 조회 시 null 반환")
    void testGetTicket_NotFound() {
        // Given (존재하지 않는 ID 설정)
        Long rId = 999L;
        when(reservationRepository.findById(rId)).thenReturn(Optional.empty());

        // When (서비스 호출)
        Ticket ticket = reservationService.getTicket(rId);

        // Then (결과 검증)
        assertNull(ticket, "존재하지 않는 예약은 null을 반환해야 함");

        // findById가 한 번만 호출되었는지 확인
        verify(reservationRepository, times(1)).findById(rId);
    }
}
