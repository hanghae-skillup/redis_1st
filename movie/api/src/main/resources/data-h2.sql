-- Genre 데이터 (6개)
INSERT INTO genre (name, description, created_by, created_at, updated_by, updated_at) VALUES
      ('액션', '스릴 넘치는 액션 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('코미디', '웃음이 가득한 코미디 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('로맨스', '달달한 로맨스 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('공포', '무서운 공포 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('SF', '미래와 상상을 담은 SF 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
      ('드라마', '감동적인 드라마 영화', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Theater 데이터 (5개)
INSERT INTO theater (name, created_by, created_at, updated_by, updated_at) VALUES
    ('1관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('2관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('3관', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('4관 - PREMIUM', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('5관 - SWEET', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Movie 데이터 (10편)
INSERT INTO movie (title, rating, genre_id, release_date, thumbnail_url, running_time, created_by, created_at, updated_by, updated_at) VALUES
   ('어벤져스: 엔드게임', 'TWELVE', 1, '2024-01-01', 'http://example.com/avengers.jpg', 180, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('인터스텔라', 'TWELVE', 5, '2024-01-05', 'http://example.com/interstellar.jpg', 170, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('라라랜드', 'ALL', 6, '2024-01-10', 'http://example.com/lalaland.jpg', 130, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('범죄도시4', 'FIFTEEN', 1, '2024-01-15', 'http://example.com/crime4.jpg', 120, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('극한직업', 'FIFTEEN', 2, '2024-01-20', 'http://example.com/extreme.jpg', 110, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('타이타닉', 'TWELVE', 6, '2024-01-25', 'http://example.com/titanic.jpg', 190, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('너의 이름은', 'ALL', 6, '2024-02-01', 'http://example.com/yourname.jpg', 110, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('오펜하이머', 'FIFTEEN', 6, '2024-02-05', 'http://example.com/oppenheimer.jpg', 180, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('메이즈 러너', 'TWELVE', 5, '2024-02-10', 'http://example.com/maze.jpg', 140, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
   ('콘크리트 유토피아', 'FIFTEEN', 6, '2024-02-15', 'http://example.com/concrete.jpg', 130, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 각 영화당 2개의 상영 정보만 생성 (총 20개)
-- 영화 1: SCHEDULED
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (1, 1, DATEADD('HOUR', 1, CURRENT_TIMESTAMP), DATEADD('HOUR', 3, CURRENT_TIMESTAMP), 'SCHEDULED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (1, 2, DATEADD('HOUR', 3, CURRENT_TIMESTAMP), DATEADD('HOUR', 5, CURRENT_TIMESTAMP), 'SCHEDULED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 2: SCHEDULED
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (2, 3, DATEADD('HOUR', 2, CURRENT_TIMESTAMP), DATEADD('HOUR', 4, CURRENT_TIMESTAMP), 'SCHEDULED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (2, 4, DATEADD('HOUR', 4, CURRENT_TIMESTAMP), DATEADD('HOUR', 6, CURRENT_TIMESTAMP), 'SCHEDULED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 3: IN_PROGRESS
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (3, 1, DATEADD('MINUTE', -30, CURRENT_TIMESTAMP), DATEADD('MINUTE', 90, CURRENT_TIMESTAMP), 'IN_PROGRESS', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (3, 2, DATEADD('MINUTE', -20, CURRENT_TIMESTAMP), DATEADD('MINUTE', 100, CURRENT_TIMESTAMP), 'IN_PROGRESS', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 4: IN_PROGRESS
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (4, 3, DATEADD('MINUTE', -25, CURRENT_TIMESTAMP), DATEADD('MINUTE', 95, CURRENT_TIMESTAMP), 'IN_PROGRESS', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (4, 4, DATEADD('MINUTE', -15, CURRENT_TIMESTAMP), DATEADD('MINUTE', 105, CURRENT_TIMESTAMP), 'IN_PROGRESS', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 5: COMPLETED
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (5, 1, DATEADD('HOUR', -3, CURRENT_TIMESTAMP), DATEADD('HOUR', -1, CURRENT_TIMESTAMP), 'COMPLETED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (5, 2, DATEADD('HOUR', -2, CURRENT_TIMESTAMP), DATEADD('HOUR', 0, CURRENT_TIMESTAMP), 'COMPLETED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 6: COMPLETED
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (6, 3, DATEADD('HOUR', -4, CURRENT_TIMESTAMP), DATEADD('HOUR', -2, CURRENT_TIMESTAMP), 'COMPLETED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (6, 4, DATEADD('HOUR', -3, CURRENT_TIMESTAMP), DATEADD('HOUR', -1, CURRENT_TIMESTAMP), 'COMPLETED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 7: CANCELLED
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (7, 1, DATEADD('HOUR', 5, CURRENT_TIMESTAMP), DATEADD('HOUR', 7, CURRENT_TIMESTAMP), 'CANCELLED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (7, 2, DATEADD('HOUR', 6, CURRENT_TIMESTAMP), DATEADD('HOUR', 8, CURRENT_TIMESTAMP), 'CANCELLED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 8: CANCELLED
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (8, 3, DATEADD('HOUR', 7, CURRENT_TIMESTAMP), DATEADD('HOUR', 9, CURRENT_TIMESTAMP), 'CANCELLED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (8, 4, DATEADD('HOUR', 8, CURRENT_TIMESTAMP), DATEADD('HOUR', 10, CURRENT_TIMESTAMP), 'CANCELLED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 9: SCHEDULED
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (9, 1, DATEADD('DAY', 1, CURRENT_TIMESTAMP), DATEADD('DAY', 1, DATEADD('HOUR', 2, CURRENT_TIMESTAMP)), 'SCHEDULED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (9, 2, DATEADD('DAY', 1, DATEADD('HOUR', 3, CURRENT_TIMESTAMP)), DATEADD('DAY', 1, DATEADD('HOUR', 5, CURRENT_TIMESTAMP)), 'SCHEDULED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- 영화 10: SCHEDULED
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
VALUES (10, 3, DATEADD('DAY', 2, CURRENT_TIMESTAMP), DATEADD('DAY', 2, DATEADD('HOUR', 2, CURRENT_TIMESTAMP)), 'SCHEDULED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);
VALUES (10, 4, DATEADD('DAY', 2, DATEADD('HOUR', 3, CURRENT_TIMESTAMP)), DATEADD('DAY', 2, DATEADD('HOUR', 5, CURRENT_TIMESTAMP)), 'SCHEDULED', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);