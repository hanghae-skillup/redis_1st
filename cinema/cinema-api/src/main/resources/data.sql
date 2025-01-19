-- 영화관 데이터 삽입
INSERT INTO cinema.cinema (id, name, address, contact_number) VALUES
    (1, '강남 메가박스', '서울 강남구 테헤란로 123', '02-123-4567');

-- 영화 데이터 삽입
INSERT INTO cinema.movie (id, title, genre, movie_rating, release_date, running_time_minutes, thumbnail_url) VALUES
                                                                                                          (1, '범죄 도시', 'CRIME', 'R', '2023-05-12', 120, 'https://example.com/images/crime_thumbnail.jpg'),
                                                                                                          (2, '로맨틱 가이드', 'ROMANCE', 'PG_13', '2023-06-01', 110, 'https://example.com/images/romance_thumbnail.jpg'),
                                                                                                          (3, '호러 나이트', 'HORROR', 'PG_13', '2023-10-15', 100, 'https://example.com/images/horror_thumbnail.jpg'),
                                                                                                          (4, 'SF 어드벤처', 'SCI_FI', 'G', '2024-01-01', 140, 'https://example.com/images/scifi_thumbnail.jpg');

-- 상영관 데이터 삽입
INSERT INTO cinema.theater (id, title, seat_layout, cinema_id) VALUES
                                                            (1, '1관', 'AAAAA,BBBBB,CCCCC,DDDDD,EEEEE', 1),
                                                            (2, '2관', 'FFFFF,GGGGG,HHHHH,IIIII,JJJJJ', 1),
                                                            (3, '3관', 'KKKKK,LLLLL,MMMMM,NNNNN,OOOOO', 1),
                                                            (4, '4관', 'PPPPP,QQQQQ,RRRRR,SSSSS,TTTTT', 1);

-- 상영 일정 데이터 삽입
INSERT INTO cinema.movie_schedule (start_at, end_at, movie_id, theater_id, cinema_id) VALUES
-- 1일차
('2024-01-01 08:00:00', '2024-01-01 10:00:00', 1, 1, 1),
('2024-01-01 10:30:00', '2024-01-01 12:30:00', 2, 2, 1),
('2024-01-01 13:00:00', '2024-01-01 15:00:00', 3, 3, 1),
('2024-01-01 15:30:00', '2024-01-01 18:00:00', 4, 4, 1),

-- 2일차
('2024-01-02 08:00:00', '2024-01-02 10:00:00', 2, 1, 1),
('2024-01-02 10:30:00', '2024-01-02 12:30:00', 3, 2, 1),
('2024-01-02 13:00:00', '2024-01-02 15:00:00', 4, 3, 1),
('2024-01-02 15:30:00', '2024-01-02 18:00:00', 1, 4, 1),

-- 3일차
('2024-01-03 08:00:00', '2024-01-03 10:00:00', 3, 1, 1),
('2024-01-03 10:30:00', '2024-01-03 12:30:00', 4, 2, 1),
('2024-01-03 13:00:00', '2024-01-03 15:00:00', 1, 3, 1),
('2024-01-03 15:30:00', '2024-01-03 18:00:00', 2, 4, 1),

-- 4일차
('2024-01-04 08:00:00', '2024-01-04 10:00:00', 4, 1, 1),
('2024-01-04 10:30:00', '2024-01-04 12:30:00', 1, 2, 1),
('2024-01-04 13:00:00', '2024-01-04 15:00:00', 2, 3, 1),
('2024-01-04 15:30:00', '2024-01-04 18:00:00', 3, 4, 1);