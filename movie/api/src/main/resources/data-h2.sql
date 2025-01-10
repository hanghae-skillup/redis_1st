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

-- 과거 완료된 상영 (COMPLETED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
SELECT
    MOD(x.n, 10) + 1,
    MOD(x.n, 5) + 1,
    DATEADD('HOUR', -x.n, CURRENT_TIMESTAMP),
    DATEADD('HOUR', -x.n + 2, CURRENT_TIMESTAMP),
    'COMPLETED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM (
         SELECT x AS n FROM SYSTEM_RANGE(1, 100)
     ) x;

-- 취소된 상영 (CANCELLED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
SELECT
    MOD(x.n, 10) + 1,
    MOD(x.n, 5) + 1,
    DATEADD('HOUR', x.n * 2, CURRENT_TIMESTAMP),
    DATEADD('HOUR', x.n * 2 + 2, CURRENT_TIMESTAMP),
    'CANCELLED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM (
         SELECT x AS n FROM SYSTEM_RANGE(1, 50)
     ) x;

-- 진행중인 상영 (IN_PROGRESS)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
SELECT
    MOD(x.n, 10) + 1,
    MOD(x.n, 5) + 1,
    DATEADD('MINUTE', -30, CURRENT_TIMESTAMP),
    DATEADD('MINUTE', 90, CURRENT_TIMESTAMP),
    'IN_PROGRESS',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM (
         SELECT x AS n FROM SYSTEM_RANGE(1, 10)
     ) x;

-- 예정된 상영 (SCHEDULED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
SELECT
    MOD(x.n, 10) + 1,
    MOD(x.n, 5) + 1,
    DATEADD('HOUR', x.n, CURRENT_TIMESTAMP),
    DATEADD('HOUR', x.n + 2, CURRENT_TIMESTAMP),
    'SCHEDULED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM (
         SELECT x AS n FROM SYSTEM_RANGE(1, 500)
     ) x;