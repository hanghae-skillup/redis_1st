package com.example.infrastructure.db.init;

import com.example.application.port.out.ScreeningSeatRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartupRunner {

    private final JdbcTemplate jdbcTemplate;
    private final ScreeningSeatRepositoryPort screeningSeatRepositoryPort;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        try {
            long screeningSeatCount = screeningSeatRepositoryPort.count();
            if (screeningSeatCount == 0) { // 데이터가 없는 경우만 실행
                generateScreeningSeatData();
                log.info("Screening Seat Data has been created");
            } else {
                log.info("Screening Seat Data already exists");
            }
        } catch (Exception e) {
            log.error("Failed to initialize Screening Seat Data", e);
        }
    }

    private void generateScreeningSeatData() {
        String sql = """
            INSERT INTO screening_seat (screening_id, seat_id, reserved, version)
            SELECT s.id, se.id, false, 0
            FROM screening s
            CROSS JOIN seat se;
        """;
        jdbcTemplate.execute(sql);
    }
}
