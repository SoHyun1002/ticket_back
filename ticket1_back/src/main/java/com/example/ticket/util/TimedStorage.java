package com.example.ticket.util;

import com.example.ticket.dto.ReservationRequest;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TimedStorage {

    // 저장 구조: key → Entry (데이터 + 저장 시각)
    private final Map<String, TimedEntry> storage = new ConcurrentHashMap<>();

    @Getter
    public static class TimedEntry {
        private final ReservationRequest request;
        private final Instant createdAt;

        public TimedEntry(ReservationRequest request) {
            this.request = request;
            this.createdAt = Instant.now();
        }
    }

    // 저장 메서드
    public void put(String key, ReservationRequest request) {
        storage.put(key, new TimedEntry(request));
    }

    // 조회 메서드
    public ReservationRequest get(String key) {
        TimedEntry entry = storage.get(key);
        return (entry != null) ? entry.getRequest() : null;
    }

    // 전체 Map 조회
    public Map<String, TimedEntry> getStorage() {
        return storage;
    }

    // 자동 삭제: 30분 이상 지난 데이터 제거
    @Scheduled(fixedRate = 60000) // 1분마다 실행
    public void cleanUpExpiredKeys() {
        Instant now = Instant.now();
        Iterator<Map.Entry<String, TimedEntry>> iterator = storage.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, TimedEntry> entry = iterator.next();
            if (entry.getValue().getCreatedAt().plusSeconds(900).isBefore(now)) {
                iterator.remove(); // 삭제
            }
        }
    }
}
