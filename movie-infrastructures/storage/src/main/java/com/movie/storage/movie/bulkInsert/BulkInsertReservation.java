package com.movie.storage.movie.bulkInsert;

import com.movie.common.enums.AxisY;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class BulkInsertReservation {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/movie2?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String BASE_INSERT_RESERVATION_SQL = "insert into reservation (schedule_id, seat_id, user_id, reserved_at, created_by, created_at, modified_by, modified_at) values ";

    private static final String BASE_INSERT_SEAT_SQL = "insert into seat (seat_number, axisY, axisX, created_by, created_at, modified_by, modified_at) values ";

    private void clearReservationTable(Connection conn) {
        String deleteSql = "TRUNCATE TABLE reservation"; // 테이블의 모든 데이터를 삭제
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(deleteSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsertReservation(int totalSchedules, int batchSize) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            clearReservationTable(conn);

            int recordCount = 0;
            StringBuilder sqlBuilder = new StringBuilder(BASE_INSERT_RESERVATION_SQL);

            int scheduleId = 1;
            int seatId = 1;

            for (int i = 1; i <= totalSchedules; i++) {
                if (i % 25 == 1 && i > 1) {
                    scheduleId++; // 25개 레코드마다 screen_id 증가
                    seatId = 1;
                }

                sqlBuilder.append(String.format(
                        "(%d, %d, %s, %s, 1, '2025-01-01', 1, '2025-01-01'),",
                        scheduleId, seatId , null, null
                ));

                seatId++;
                recordCount++;

                // 배치 처리
                if (recordCount == batchSize) {
                    commitQuery(sqlBuilder, conn);

                    sqlBuilder.setLength(0); // SQL 빌더 초기화
                    sqlBuilder.append(BASE_INSERT_RESERVATION_SQL);
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

    private void clearSeatTable(Connection conn) {
        String deleteSql = "TRUNCATE TABLE seat";           // 테이블의 모든 데이터를 삭제
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(deleteSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsertSeat(int totalSchedules, int batchSize) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            clearSeatTable(conn);

            int recordCount = 0;
            StringBuilder sqlBuilder = new StringBuilder(BASE_INSERT_SEAT_SQL);

            AxisY[] axisYValues = AxisY.values(); // 열거형 값 배열
            String axisY = axisYValues[0].name(); // 초기값
            int axisX = 1;

            for (int i = 1; i <= totalSchedules; i++) {
                if ((i - 1) % 5 == 0 && i > 1) {
                    int index = (i - 1) / 5 % axisYValues.length;
                    axisY = axisYValues[index].name();
                    axisX = 1; // axisX 초기화
                }

                sqlBuilder.append(String.format(
                        "('%s', '%s', %d, 1, '2025-01-01', 1, '2025-01-01'),",
                        "%s%d".formatted(axisY, axisX), axisY, axisX
                ));

                axisX++;
                recordCount++;

                // 배치 처리
                if (recordCount == batchSize) {
                    commitQuery(sqlBuilder, conn);

                    sqlBuilder.setLength(0); // SQL 빌더 초기화
                    sqlBuilder.append(BASE_INSERT_SEAT_SQL);
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
