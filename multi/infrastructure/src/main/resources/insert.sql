
INSERT INTO Movies (created_at, created_by, updated_at, updated_by, genre, rating, release_date, running_time, thumbnail_url, title, movie_id)
VALUES
    (NOW(), 'admin', NOW(), 'admin', 'Action', 'PG-13', '2025-01-01', 120, 'https://example.com/movie1.jpg', 'Action Movie 1',1),
    (NOW(), 'admin', NOW(), 'admin', 'Comedy', 'G', '2025-02-01', 90, 'https://example.com/movie2.jpg', 'Comedy Movie 2',2);

INSERT INTO Theaters (theater_id, theater_name, created_by, created_at, updated_by, updated_at)
VALUES
    (1, 'Theater A', 'admin', NOW(), 'admin', NOW()),
    (2, 'Theater B', 'admin', NOW(), 'admin', NOW());

INSERT INTO Screenings (screening_id, movie_id, theater_id, start_time, end_time, screening_date, created_by, created_at, updated_by, updated_at)
VALUES
    (1, 1, 1, '2025-01-10 14:00:00', '2025-01-10 16:00:00', '2025-01-10', 'admin', NOW(), 'admin', NOW()),
    (2, 1, 2, '2025-01-11 14:00:00', '2025-01-11 16:00:00', '2025-01-10', 'admin', NOW(), 'admin', NOW()),
    (3, 2, 2, '2025-01-11 18:00:00', '2025-01-11 20:00:00', '2025-01-11', 'admin', NOW(), 'admin', NOW());

INSERT INTO SeatReservations (seat_reservation_id, screening_id, seat_number, is_reserved, created_by, created_at, updated_by, updated_at)
VALUES
    (1, 1, 'A1', 1, 'admin', NOW(), 'admin', NOW()),
    (2, 1, 'A2', 0, 'admin', NOW(), 'admin', NOW()),
    (3, 2, 'B1', 1, 'admin', NOW(), 'admin', NOW()),
    (4, 2, 'B2', 0, 'admin', NOW(), 'admin', NOW());