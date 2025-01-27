DELETE FROM reservations;
DELETE FROM schedule;
DELETE FROM seat;
DELETE FROM users;
DELETE FROM movie;
DELETE FROM theater;

INSERT INTO movie (id, title, grade, genre, running_time, release_date, thumbnail_url, created_by, created_at, updated_by, updated_at)
VALUES (25, '웡카', '전체관람가', '판타지', 116, '2024-01-31', 'https://example.com/wonka.jpg', 'SYSTEM', NOW(), 'SYSTEM', NOW());

INSERT INTO theater (id, name, created_by, created_at, updated_by, updated_at)
VALUES (51, '1관', 'SYSTEM', NOW(), 'SYSTEM', NOW()),
       (52, '2관', 'SYSTEM', NOW(), 'SYSTEM', NOW());

INSERT INTO schedule (movie_id, theater_id, start_at, end_at, created_by, created_at, updated_by, updated_at)
VALUES (25, 51, '2024-02-14 10:00:00', '2024-02-14 12:00:00', 'SYSTEM', NOW(), 'SYSTEM', NOW()),
       (25, 52, '2024-02-14 11:00:00', '2024-02-14 13:00:00', 'SYSTEM', NOW(), 'SYSTEM', NOW());

INSERT INTO users (name, email, password, created_by, created_at, updated_by, updated_at)
VALUES ('John Doe', 'john@example.com', 'password123', 'SYSTEM', NOW(), 'SYSTEM', NOW()),
       ('Jane Smith', 'jane@example.com', 'password456', 'SYSTEM', NOW(), 'SYSTEM', NOW());

INSERT INTO seat (theater_id, seat_number, seat_row, seat_column, created_by, created_at, updated_by, updated_at)
VALUES (51, 'A1', 'A', 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
       (51, 'A2', 'A', 2, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
       (52, 'A1', 'A', 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
       (52, 'A2', 'A', 2, 'SYSTEM', NOW(), 'SYSTEM', NOW()); 