-- MOVIE 초기 데이터 삽입
INSERT INTO movie (title, release_date, thumbnail_url, runtime_minutes, genre, rating)
VALUES ('기생충', '2019-05-30', 'https://example.com/parasite.jpg', 132, '드라마', '15세 관람가'),
       ('범죄도시 2', '2022-05-18', 'https://example.com/crimecity2.jpg', 106, '액션', '15세 관람가'),
       ('명량', '2014-07-30', 'https://example.com/myeongryang.jpg', 126, '전쟁', '12세 관람가'),
       ('신과 함께: 죄와 벌', '2017-12-20', 'https://example.com/alongwithgods.jpg', 139, '판타지', '12세 관람가');

-- SCREEN 초기 데이터 삽입
INSERT INTO screen (name, seat_row, seat_column)
VALUES ('메가박스 코엑스 MX관', 5, 5),
       ('CGV 용산 아이맥스관', 5, 5);

-- SHOWTIME 초기 데이터 삽입
INSERT INTO show_time (movie_id, screen_id, start_time)
VALUES ((SELECT id FROM MOVIE WHERE title = '기생충' LIMIT 1),
        (SELECT id FROM SCREEN WHERE name = '메가박스 코엑스 MX관' LIMIT 1),
        '2025-01-11 14:30:00'),
       ((SELECT id FROM MOVIE WHERE title = '범죄도시 2' LIMIT 1),
        (SELECT id FROM SCREEN WHERE name = 'CGV 용산 아이맥스관' LIMIT 1),
        '2025-01-11 17:00:00'),
       ((SELECT id FROM MOVIE WHERE title = '명량' LIMIT 1),
        (SELECT id FROM SCREEN WHERE name = '메가박스 코엑스 MX관' LIMIT 1),
        '2025-01-11 20:30:00'),
       ((SELECT id FROM MOVIE WHERE title = '신과 함께: 죄와 벌' LIMIT 1),
        (SELECT id FROM SCREEN WHERE name = 'CGV 용산 아이맥스관' LIMIT 1),
        '2025-01-12 00:00:00');