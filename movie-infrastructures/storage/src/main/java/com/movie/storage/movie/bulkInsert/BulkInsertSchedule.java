package com.movie.storage.movie.bulkInsert;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class BulkInsertSchedule {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/movie?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String BASE_INSERT_SQL = "INSERT INTO schedule (theater_id, screen_id, movie_id, start_time, end_time, created_by, created_at, modified_by, modified_at) VALUES ";

    // schedule 테이블을 비우는 메소드
    private void clearTable(Connection conn) throws SQLException {
        String deleteSql = "TRUNCATE TABLE schedule"; // 테이블의 모든 데이터를 삭제
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(deleteSql);
        }
    }

    public void bulkInsertSchedules(int totalSchedules, int batchSize) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            // schedule 테이블 비우기
            clearTable(conn);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            int recordCount = 0;

            StringBuilder sqlBuilder = new StringBuilder(BASE_INSERT_SQL);

            // 시작 시간 설정
            // 시작 시간 기본값
            LocalDateTime initialStartTime = LocalDateTime.of(2025, 1, 8, 8, 0);
            LocalDateTime startTime = initialStartTime;

            int theaterId = 1; // 초기 theater_id
            int screenId = 1;  // 초기 screen_id
            int movieId = 1;   // 초기 movie_id

            Random random = new Random();

            for (int i = 1; i <= totalSchedules; i++) {
                if (i % 100 == 1 && i > 1) {
                    theaterId++; // 80개 레코드마다 theater_id 증가
                }
                if (i % 10 == 1 && i > 1) {
                    screenId++; // 8개 레코드마다 movie_id 증가
                }
                if (i % 10 == 1 && i > 1) {
                    movieId = random.nextInt(500) + 1; // 1 ~ 500 사이의 랜덤 movie_id 생성; // 8개 레코드마다 movie_id 증가
                }

                LocalDateTime endTime = startTime.plusHours(1); // 시작 시간으로부터 2시간 추가

                if (recordCount > 0) {
                    sqlBuilder.append(",");
                }

                sqlBuilder.append("(")
                        .append(theaterId).append(", ")
                        .append(screenId).append(", ")
                        .append(movieId).append(", '")
                        .append(startTime.format(formatter)).append("', '")
                        .append(endTime.format(formatter)).append("', ")
                        .append("1, '2025-01-01', 1, '2025-01-01')");

                recordCount++;
                startTime = endTime; // 다음 시작 시간으로 업데이트

                // 10개의 레코드를 처리할 때마다 startTime 초기화
                if (i % 10 == 0) {
                    startTime = initialStartTime;
                }

                // 배치 처리
                if (recordCount == batchSize) {
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlBuilder.toString())) {
                        pstmt.executeUpdate();
                        conn.commit();
                    } catch (SQLException e) {
                        conn.rollback();
                        e.printStackTrace();
                    }

                    sqlBuilder.setLength(0); // SQL 빌더 초기화
                    sqlBuilder.append(BASE_INSERT_SQL);
                    recordCount = 0;
                }
            }

            // 남아 있는 배치 처리
            if (recordCount > 0) {
                try (PreparedStatement pstmt = conn.prepareStatement(sqlBuilder.toString())) {
                    pstmt.executeUpdate();
                    conn.commit();
                } catch (SQLException e) {
                    conn.rollback();
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

