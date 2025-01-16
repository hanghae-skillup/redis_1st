package com.movie.storage.movie.bulkInsert;

import com.movie.moviedomain.enums.FilmRating;
import com.movie.moviedomain.enums.Genre;
import com.movie.moviedomain.movie.domain.Movie;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class BulkInsertMovie {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/movie?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String BASE_INSERT_SQL = "insert into movie (title, film_rating, genre, thumbnail_url, running_time, released_at, created_by, created_at, modified_by, modified_at) values ";

    // schedule 테이블을 비우는 메소드
    private void clearTable(Connection conn) throws SQLException {
        String deleteSql = "TRUNCATE TABLE movie"; // 테이블의 모든 데이터를 삭제
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(deleteSql);
        }
    }

    // 랜덤한 FilmRating 반환
    private static FilmRating getRandomFilmRating() {
        FilmRating[] ratings = FilmRating.values();
        return ratings[(int) (Math.random() * ratings.length)];
    }

    // 랜덤한 장르 반환
    private static Genre getRandomGenre() {
        Genre[] genres = Genre.values();
        return genres[(int) (Math.random() * genres.length)];
    }

    // 랜덤한 시간을 반환 (90~180분)
    private static int getRandomRunningTime() {
        return 90 + (int) (Math.random() * 91); // 90~180
    }

    // 랜덤한 날짜 반환
    private static Date getRandomReleaseDate() {
        long minDay = java.sql.Date.valueOf("2024-12-01").getTime();
        long maxDay = java.sql.Date.valueOf("2024-12-31").getTime();
        long randomDay = minDay + (long) (Math.random() * (maxDay - minDay));
        return new Date(randomDay);
    }

    // 영화 데이터를 삽입하는 메서드
    public void insertMovie(int totalMovies, int batchSize) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            // 테이블 비우기
            clearTable(conn);

            int recordCount = 0;
            StringBuilder sqlBuilder = new StringBuilder(BASE_INSERT_SQL);

            for (int i = 1; i <= totalMovies; i++) {
                String title = "영화" + i;
                FilmRating filmRating = getRandomFilmRating();
                Genre genre = getRandomGenre();
                String thumbnailUrl = "https://example.com/thumbnails/" + i + ".jpg";
                int runningTime = getRandomRunningTime();
                Date releasedAt = getRandomReleaseDate();

                sqlBuilder.append(String.format(
                        "('%s', %d, '%s', '%s', %d, '%s', 'admin', '2025-01-01', 'admin', '2025-01-01'),",
                        title, filmRating.getValue(), genre, thumbnailUrl, runningTime, releasedAt
                ));
                recordCount++;

                // 배치 처리
                if (recordCount == batchSize) {
                    commitQuery(sqlBuilder, conn);
                    sqlBuilder.setLength(0); // SQL 빌더 초기화
                    sqlBuilder.append(BASE_INSERT_SQL);
                    recordCount = 0;
                }
            }

            // 남은 데이터 삽입
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
