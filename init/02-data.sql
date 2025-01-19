-- Movie Table
INSERT INTO movie (id, release_date, running_time, created_at, updated_at, thumbnail_url, title, genre, rating) VALUES
(1, DATE '2025-01-01', 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'https://example.com/avengers.jpg', 'Avengers', 'ACTION', 'ALL'),
(2, DATE '2025-01-03', 105, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'https://example.com/conjuring.jpg', 'Conjuring', 'HORROR', 'FIFTEEN'),
(3, DATE '2024-12-25', 195, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'https://example.com/titanic.jpg', 'Titanic', 'ROMANCE', 'TWELVE'),
(4, DATE '2024-12-30', 169, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'https://example.com/interstellar.jpg', 'Interstellar', 'SF', 'ALL');

-- Theater Table
INSERT INTO theater (id, created_at, updated_at, name) VALUES
(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Grand Cinema'),
(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Cineplex'),
(3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Mega Theater');

-- Movie_Theater Table
INSERT INTO movie_theater (id, created_at, updated_at, movie_id, theater_id) VALUES
(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2),
(3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 1),
(4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 3);

INSERT INTO screening (id, screening_at, created_at, updated_at, started_at, ended_at, movie_id) VALUES
-- 영화 1
(1, DATE '2025-01-10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-10 10:00:00', TIMESTAMP '2025-01-10 12:00:00', 1),
(2, DATE '2025-01-10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-10 13:00:00', TIMESTAMP '2025-01-10 15:00:00', 1),
(3, DATE '2025-01-11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-11 16:00:00', TIMESTAMP '2025-01-11 18:00:00', 1),
(4, DATE '2025-01-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-12 19:00:00', TIMESTAMP '2025-01-12 21:00:00', 1),
(5, DATE '2025-01-13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-13 10:30:00', TIMESTAMP '2025-01-13 12:30:00', 1),

-- 영화 2
(6, DATE '2025-01-11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-11 14:00:00', TIMESTAMP '2025-01-11 16:00:00', 2),
(7, DATE '2025-01-11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-11 16:30:00', TIMESTAMP '2025-01-11 18:30:00', 2),
(8, DATE '2025-01-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-12 13:00:00', TIMESTAMP '2025-01-12 15:00:00', 2),
(9, DATE '2025-01-13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-13 15:30:00', TIMESTAMP '2025-01-13 17:30:00', 2),

-- 영화 3
(10, DATE '2025-01-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-12 18:00:00', TIMESTAMP '2025-01-12 21:15:00', 3),
(11, DATE '2025-01-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-12 20:00:00', TIMESTAMP '2025-01-12 22:15:00', 3),
(12, DATE '2025-01-13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-13 14:00:00', TIMESTAMP '2025-01-13 16:45:00', 3),
(13, DATE '2025-01-14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-14 10:00:00', TIMESTAMP '2025-01-14 12:30:00', 3),

-- 영화 4
(14, DATE '2025-01-13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-13 20:00:00', TIMESTAMP '2025-01-13 22:45:00', 4),
(15, DATE '2025-01-13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-13 21:30:00', TIMESTAMP '2025-01-14 00:15:00', 4),
(16, DATE '2025-01-14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-14 11:00:00', TIMESTAMP '2025-01-14 13:30:00', 4),
(17, DATE '2025-01-14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-14 15:00:00', TIMESTAMP '2025-01-14 17:45:00', 4),
(18, DATE '2025-01-14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TIMESTAMP '2025-01-14 19:00:00', TIMESTAMP '2025-01-14 21:30:00', 4);


-- Seat Table
-- For each screening, generate a 5x5 grid of seats
INSERT INTO seat (reserved, screening_id, seat_number) VALUES
-- Seats for Screening 1
(0, 1, 'A1'), (0, 1, 'A2'), (0, 1, 'A3'), (0, 1, 'A4'), (0, 1, 'A5'),
(0, 1, 'B1'), (0, 1, 'B2'), (0, 1, 'B3'), (0, 1, 'B4'), (0, 1, 'B5'),
(0, 1, 'C1'), (0, 1, 'C2'), (0, 1, 'C3'), (0, 1, 'C4'), (0, 1, 'C5'),
(0, 1, 'D1'), (0, 1, 'D2'), (0, 1, 'D3'), (0, 1, 'D4'), (0, 1, 'D5'),
(0, 1, 'E1'), (0, 1, 'E2'), (0, 1, 'E3'), (0, 1, 'E4'), (0, 1, 'E5'),

-- Seats for Screening 2
(0, 2, 'A1'), (0, 2, 'A2'), (0, 2, 'A3'), (0, 2, 'A4'), (0, 2, 'A5'),
(0, 2, 'B1'), (0, 2, 'B2'), (0, 2, 'B3'), (0, 2, 'B4'), (0, 2, 'B5'),
(0, 2, 'C1'), (0, 2, 'C2'), (0, 2, 'C3'), (0, 2, 'C4'), (0, 2, 'C5'),
(0, 2, 'D1'), (0, 2, 'D2'), (0, 2, 'D3'), (0, 2, 'D4'), (0, 2, 'D5'),
(0, 2, 'E1'), (0, 2, 'E2'), (0, 2, 'E3'), (0, 2, 'E4'), (0, 2, 'E5'),

-- Seats for Screening 3
(0, 3, 'A1'), (0, 3, 'A2'), (0, 3, 'A3'), (0, 3, 'A4'), (0, 3, 'A5'),
(0, 3, 'B1'), (0, 3, 'B2'), (0, 3, 'B3'), (0, 3, 'B4'), (0, 3, 'B5'),
(0, 3, 'C1'), (0, 3, 'C2'), (0, 3, 'C3'), (0, 3, 'C4'), (0, 3, 'C5'),
(0, 3, 'D1'), (0, 3, 'D2'), (0, 3, 'D3'), (0, 3, 'D4'), (0, 3, 'D5'),
(0, 3, 'E1'), (0, 3, 'E2'), (0, 3, 'E3'), (0, 3, 'E4'), (0, 3, 'E5'),

-- Seats for Screening 4
(0, 4, 'A1'), (0, 4, 'A2'), (0, 4, 'A3'), (0, 4, 'A4'), (0, 4, 'A5'),
(0, 4, 'B1'), (0, 4, 'B2'), (0, 4, 'B3'), (0, 4, 'B4'), (0, 4, 'B5'),
(0, 4, 'C1'), (0, 4, 'C2'), (0, 4, 'C3'), (0, 4, 'C4'), (0, 4, 'C5'),
(0, 4, 'D1'), (0, 4, 'D2'), (0, 4, 'D3'), (0, 4, 'D4'), (0, 4, 'D5'),
(0, 4, 'E1'), (0, 4, 'E2'), (0, 4, 'E3'), (0, 4, 'E4'), (0, 4, 'E5');
