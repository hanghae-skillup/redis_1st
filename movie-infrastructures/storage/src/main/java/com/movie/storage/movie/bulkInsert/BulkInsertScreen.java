package com.movie.storage.movie.bulkInsert;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class BulkInsertScreen {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/movie?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String BASE_INSERT_SQL_SCREEN = "insert into screen (theater_id, name, created_by, created_at, modified_by, modified_at) values ";
    private static final String BASE_INSERT_SQL_THEATER = "insert into theater (name, created_by, created_at, modified_by, modified_at) values ";

    // schedule 테이블을 비우는 메소드
    private void clearScreenTable(Connection conn) throws SQLException {
        String deleteSql = "TRUNCATE TABLE screen"; // 테이블의 모든 데이터를 삭제
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(deleteSql);
        }
    }

    public void bulkInsertScreens(int totalSchedules, int batchSize) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            // schedule 테이블 비우기
            clearScreenTable(conn);

            int recordCount = 0;
            StringBuilder sqlBuilder = new StringBuilder(BASE_INSERT_SQL_SCREEN);

            int theaterId = 1;
            int screenId = 1;

            for (int i = 1; i <= totalSchedules; i++) {
                if (i % 10 == 1 && i > 1) {
                    theaterId++; // 10개 레코드마다 theater_id 증가
                }
                String name = "%d관".formatted(screenId);

                sqlBuilder.append(String.format(
                        "('%d', '%s', 'admin', '2025-01-01', 'admin', '2025-01-01'),",
                        theaterId, name
                ));

                recordCount++;
                screenId++;
                if (i % 10 == 0) {
                    screenId = 1;
                }

                // 배치 처리
                if (recordCount == batchSize) {
                    commitQuery(sqlBuilder, conn);

                    sqlBuilder.setLength(0); // SQL 빌더 초기화
                    sqlBuilder.append(BASE_INSERT_SQL_SCREEN);
                    recordCount = 0;
                }
            }

            // 남아 있는 배치 처리
            if (recordCount > 0) {
                commitQuery(sqlBuilder, conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // schedule 테이블을 비우는 메소드
    private void clearTheaterTable(Connection conn) throws SQLException {
        String deleteSql = "TRUNCATE TABLE theater"; // 테이블의 모든 데이터를 삭제
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(deleteSql);
        }
    }

    public void bulkInsertTheater(int totalSchedules, int batchSize) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            // schedule 테이블 비우기
            clearTheaterTable(conn);

            int recordCount = 0;
            StringBuilder sqlBuilder = new StringBuilder(BASE_INSERT_SQL_THEATER);

            int theaterId = 1;

            for (int i = 1; i <= totalSchedules; i++) {
//                if (i % 10 == 1 && i > 1) {
//                    theaterId++; // 10개 레코드마다 theater_id 증가
//                }
                String name = "영화관%d".formatted(theaterId);

                sqlBuilder.append(String.format(
                        "('%s', 'admin', '2025-01-01', 'admin', '2025-01-01'),",
                        name
                ));

                recordCount++;
                theaterId++;

                // 배치 처리
                if (recordCount == batchSize) {
                    commitQuery(sqlBuilder, conn);

                    sqlBuilder.setLength(0); // SQL 빌더 초기화
                    sqlBuilder.append(BASE_INSERT_SQL_THEATER);
                    recordCount = 0;
                }
            }

            // 남아 있는 배치 처리
            if (recordCount > 0) {
                commitQuery(sqlBuilder, conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void commitQuery(StringBuilder sqlBuilder, Connection conn) throws SQLException {
        String sql = sqlBuilder.substring(0, sqlBuilder.length() - 1);
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
    }

}
