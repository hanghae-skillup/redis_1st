-- 영화 데이터
INSERT INTO movie (title, rating, release_date, thumbnail_url, running_time, genre, created_at, created_by, updated_at, updated_by)
VALUES 
('범죄도시4', 'FIFTEEN', '2024-05-01', 'https://example.com/movie1.jpg', 120, 'ACTION', NOW(), 'system', NOW(), 'system'),
('어벤져스5', 'TWELVE', '2024-05-15', 'https://example.com/movie2.jpg', 150, 'SF', NOW(), 'system', NOW(), 'system');

-- 상영관 데이터
INSERT INTO theater (name, created_at, created_by, updated_at, updated_by)
VALUES 
('1관', NOW(), 'system', NOW(), 'system'),
('2관', NOW(), 'system', NOW(), 'system');

-- 상영 일정 데이터
INSERT INTO screening (movie_id, theater_id, start_time, created_at, created_by, updated_at, updated_by)
SELECT 
    m.id as movie_id,
    t.id as theater_id,
    -- 현재 시간에서 시간은 numbers.n * 2를 더하고, 분은 5의 배수로, 초는 0으로 설정
    DATE_FORMAT(
        DATE_ADD(
            DATE_FORMAT(NOW(), '%Y-%m-%d %H:00:00'),
            INTERVAL (numbers.n * 2) HOUR
        ),
        '%Y-%m-%d %H:%i:00'
    ) as start_time,
    NOW() as created_at,
    'system' as created_by,
    NOW() as updated_at,
    'system' as updated_by
FROM movie m
CROSS JOIN theater t
CROSS JOIN (
    SELECT a.N + b.N * 10 + c.N * 100 as n
    FROM (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a
    CROSS JOIN (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b
    CROSS JOIN (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) c
    LIMIT 500
) numbers
CROSS JOIN (
    SELECT 0 as minute
    UNION SELECT 5
    UNION SELECT 10
    UNION SELECT 15
    UNION SELECT 20
    UNION SELECT 25
    UNION SELECT 30
    UNION SELECT 35
    UNION SELECT 40
    UNION SELECT 45
    UNION SELECT 50
    UNION SELECT 55
) minutes;
