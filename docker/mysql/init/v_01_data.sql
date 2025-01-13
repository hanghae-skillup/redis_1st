-- movie 테이블에 10개의 영화 삽입
INSERT INTO movie.movie (created_at, updated_at, age_rating, category, duration, release_date, theater, title)
VALUES
    (NOW(), NOW(), 'FIFTEEN', 'ACTION', 120, '2023-01-01', 'ROOM_1', 'Action Movie 1'),
    (NOW(), NOW(), 'NINETEEN', 'COMEDY', 90, '2023-02-01', 'ROOM_2', 'Comedy Movie 1'),
    (NOW(), NOW(), 'TWELVE', 'DRAMA', 110, '2023-03-01', 'ROOM_3', 'Drama Movie 1'),
    (NOW(), NOW(), 'FIFTEEN', 'FANTASY', 130, '2023-04-01', 'ROOM_4', 'Fantasy Movie 1'),
    (NOW(), NOW(), 'NINETEEN', 'HORROR', 100, '2023-05-01', 'ROOM_5', 'Horror Movie 1'),
    (NOW(), NOW(), 'TWELVE', 'MYSTERY', 115, '2023-06-01', 'ROOM_6', 'Mystery Movie 1'),
    (NOW(), NOW(), 'FIFTEEN', 'ROMANCE', 105, '2023-07-01', 'ROOM_1', 'Romance Movie 1'),
    (NOW(), NOW(), 'NINETEEN', 'THRILLER', 95, '2023-08-01', 'ROOM_2', 'Thriller Movie 1'),
    (NOW(), NOW(), 'TWELVE', 'ACTION', 125, '2023-09-01', 'ROOM_3', 'Action Movie 2'),
    (NOW(), NOW(), 'FIFTEEN', 'COMEDY', 85, '2023-10-01', 'ROOM_4', 'Comedy Movie 2');

-- movie_thumbnail 테이블에 10개의 썸네일 삽입
INSERT INTO movie.movie_thumbnail (created_at, updated_at, path, url, movie_id)
VALUES
    (NOW(), NOW(), '/path/to/thumbnail1.jpg', 'http://example.com/thumbnail1.jpg', 1),
    (NOW(), NOW(), '/path/to/thumbnail2.jpg', 'http://example.com/thumbnail2.jpg', 2),
    (NOW(), NOW(), '/path/to/thumbnail3.jpg', 'http://example.com/thumbnail3.jpg', 3),
    (NOW(), NOW(), '/path/to/thumbnail4.jpg', 'http://example.com/thumbnail4.jpg', 4),
    (NOW(), NOW(), '/path/to/thumbnail5.jpg', 'http://example.com/thumbnail5.jpg', 5),
    (NOW(), NOW(), '/path/to/thumbnail6.jpg', 'http://example.com/thumbnail6.jpg', 6),
    (NOW(), NOW(), '/path/to/thumbnail7.jpg', 'http://example.com/thumbnail7.jpg', 7),
    (NOW(), NOW(), '/path/to/thumbnail8.jpg', 'http://example.com/thumbnail8.jpg', 8),
    (NOW(), NOW(), '/path/to/thumbnail9.jpg', 'http://example.com/thumbnail9.jpg', 9),
    (NOW(), NOW(), '/path/to/thumbnail10.jpg', 'http://example.com/thumbnail10.jpg', 10);

-- 각 영화에 대해 10개의 스케줄 삽입
INSERT INTO movie.schedule (created_at, updated_at, end_at, start_at, movie_id)
VALUES
    (NOW(), NOW(), '12:00:00', '10:00:00', 1),
    (NOW(), NOW(), '14:00:00', '12:00:00', 1),
    (NOW(), NOW(), '16:00:00', '14:00:00', 1),
    (NOW(), NOW(), '18:00:00', '16:00:00', 1),
    (NOW(), NOW(), '20:00:00', '18:00:00', 1),
    (NOW(), NOW(), '22:00:00', '20:00:00', 1),
    (NOW(), NOW(), '00:00:00', '22:00:00', 1),
    (NOW(), NOW(), '02:00:00', '00:00:00', 1),
    (NOW(), NOW(), '04:00:00', '02:00:00', 1),
    (NOW(), NOW(), '06:00:00', '04:00:00', 1),

    (NOW(), NOW(), '12:00:00', '10:00:00', 2),
    (NOW(), NOW(), '14:00:00', '12:00:00', 2),
    (NOW(), NOW(), '16:00:00', '14:00:00', 2),
    (NOW(), NOW(), '18:00:00', '16:00:00', 2),
    (NOW(), NOW(), '20:00:00', '18:00:00', 2),
    (NOW(), NOW(), '22:00:00', '20:00:00', 2),
    (NOW(), NOW(), '00:00:00', '22:00:00', 2),
    (NOW(), NOW(), '02:00:00', '00:00:00', 2),
    (NOW(), NOW(), '04:00:00', '02:00:00', 2),
    (NOW(), NOW(), '06:00:00', '04:00:00', 2),

    (NOW(), NOW(), '12:00:00', '10:00:00', 3),
    (NOW(), NOW(), '14:00:00', '12:00:00', 3),
    (NOW(), NOW(), '16:00:00', '14:00:00', 3),
    (NOW(), NOW(), '18:00:00', '16:00:00', 3),
    (NOW(), NOW(), '20:00:00', '18:00:00', 3),
    (NOW(), NOW(), '22:00:00', '20:00:00', 3),
    (NOW(), NOW(), '00:00:00', '22:00:00', 3),
    (NOW(), NOW(), '02:00:00', '00:00:00', 3),
    (NOW(), NOW(), '04:00:00', '02:00:00', 3),
    (NOW(), NOW(), '06:00:00', '04:00:00', 3),

    (NOW(), NOW(), '12:00:00', '10:00:00', 4),
    (NOW(), NOW(), '14:00:00', '12:00:00', 4),
    (NOW(), NOW(), '16:00:00', '14:00:00', 4),
    (NOW(), NOW(), '18:00:00', '16:00:00', 4),
    (NOW(), NOW(), '20:00:00', '18:00:00', 4),
    (NOW(), NOW(), '22:00:00', '20:00:00', 4),
    (NOW(), NOW(), '00:00:00', '22:00:00', 4),
    (NOW(), NOW(), '02:00:00', '00:00:00', 4),
    (NOW(), NOW(), '04:00:00', '02:00:00', 4),
    (NOW(), NOW(), '06:00:00', '04:00:00', 4),

    (NOW(), NOW(), '12:00:00', '10:00:00', 5),
    (NOW(), NOW(), '14:00:00', '12:00:00', 5),
    (NOW(), NOW(), '16:00:00', '14:00:00', 5),
    (NOW(), NOW(), '18:00:00', '16:00:00', 5),
    (NOW(), NOW(), '20:00:00', '18:00:00', 5),
    (NOW(), NOW(), '22:00:00', '20:00:00', 5),
    (NOW(), NOW(), '00:00:00', '22:00:00', 5),
    (NOW(), NOW(), '02:00:00', '00:00:00', 5),
    (NOW(), NOW(), '04:00:00', '02:00:00', 5),
    (NOW(), NOW(), '06:00:00', '04:00:00', 5),

    (NOW(), NOW(), '12:00:00', '10:00:00', 6),
    (NOW(), NOW(), '14:00:00', '12:00:00', 6),
    (NOW(), NOW(), '16:00:00', '14:00:00', 6),
    (NOW(), NOW(), '18:00:00', '16:00:00', 6),
    (NOW(), NOW(), '20:00:00', '18:00:00', 6),
    (NOW(), NOW(), '22:00:00', '20:00:00', 6),
    (NOW(), NOW(), '00:00:00', '22:00:00', 6),
    (NOW(), NOW(), '02:00:00', '00:00:00', 6),
    (NOW(), NOW(), '04:00:00', '02:00:00', 6),
    (NOW(), NOW(), '06:00:00', '04:00:00', 6),

    (NOW(), NOW(), '12:00:00', '10:00:00', 7),
    (NOW(), NOW(), '14:00:00', '12:00:00', 7),
    (NOW(), NOW(), '16:00:00', '14:00:00', 7),
    (NOW(), NOW(), '18:00:00', '16:00:00', 7),
    (NOW(), NOW(), '20:00:00', '18:00:00', 7),
    (NOW(), NOW(), '22:00:00', '20:00:00', 7),
    (NOW(), NOW(), '00:00:00', '22:00:00', 7),
    (NOW(), NOW(), '02:00:00', '00:00:00', 7),
    (NOW(), NOW(), '04:00:00', '02:00:00', 7),
    (NOW(), NOW(), '06:00:00', '04:00:00', 7),

    (NOW(), NOW(), '12:00:00', '10:00:00', 8),
    (NOW(), NOW(), '14:00:00', '12:00:00', 8),
    (NOW(), NOW(), '16:00:00', '14:00:00', 8),
    (NOW(), NOW(), '18:00:00', '16:00:00', 8),
    (NOW(), NOW(), '20:00:00', '18:00:00', 8),
    (NOW(), NOW(), '22:00:00', '20:00:00', 8),
    (NOW(), NOW(), '00:00:00', '22:00:00', 8),
    (NOW(), NOW(), '02:00:00', '00:00:00', 8),
    (NOW(), NOW(), '04:00:00', '02:00:00', 8),
    (NOW(), NOW(), '06:00:00', '04:00:00', 8),

    (NOW(), NOW(), '12:00:00', '10:00:00', 9),
    (NOW(), NOW(), '14:00:00', '12:00:00', 9),
    (NOW(), NOW(), '16:00:00', '14:00:00', 9),
    (NOW(), NOW(), '18:00:00', '16:00:00', 9),
    (NOW(), NOW(), '20:00:00', '18:00:00', 9),
    (NOW(), NOW(), '22:00:00', '20:00:00', 9),
    (NOW(), NOW(), '00:00:00', '22:00:00', 9),
    (NOW(), NOW(), '02:00:00', '00:00:00', 9),
    (NOW(), NOW(), '04:00:00', '02:00:00', 9),
    (NOW(), NOW(), '06:00:00', '04:00:00', 9),

    (NOW(), NOW(), '12:00:00', '10:00:00', 10),
    (NOW(), NOW(), '14:00:00', '12:00:00', 10),
    (NOW(), NOW(), '16:00:00', '14:00:00', 10),
    (NOW(), NOW(), '18:00:00', '16:00:00', 10),
    (NOW(), NOW(), '20:00:00', '18:00:00', 10),
    (NOW(), NOW(), '22:00:00', '20:00:00', 10),
    (NOW(), NOW(), '00:00:00', '22:00:00', 10),
    (NOW(), NOW(), '02:00:00', '00:00:00', 10),
    (NOW(), NOW(), '04:00:00', '02:00:00', 10),
    (NOW(), NOW(), '06:00:00', '04:00:00', 10);


-- 각 스케줄별 좌석별 티켓 삽입
INSERT INTO movie.ticket (created_at, updated_at, seats_col, seats_row, schedule_id)
SELECT NOW(), NOW(), seats_col, seats_row, schedule_id
FROM (
         SELECT 'A' AS seats_col, 'R_1' AS seats_row, schedule.id AS schedule_id FROM movie.schedule
         UNION ALL
         SELECT 'A', 'R_2', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'A', 'R_3', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'A', 'R_4', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'A', 'R_5', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'B', 'R_1', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'B', 'R_2', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'B', 'R_3', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'B', 'R_4', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'B', 'R_5', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'C', 'R_1', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'C', 'R_2', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'C', 'R_3', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'C', 'R_4', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'C', 'R_5', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'D', 'R_1', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'D', 'R_2', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'D', 'R_3', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'D', 'R_4', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'D', 'R_5', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'E', 'R_1', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'E', 'R_2', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'E', 'R_3', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'E', 'R_4', schedule.id FROM movie.schedule
         UNION ALL
         SELECT 'E', 'R_5', schedule.id FROM movie.schedule
     ) AS seats;