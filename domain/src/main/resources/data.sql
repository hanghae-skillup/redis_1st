SET SESSION cte_max_recursion_depth = 1000000;

DELETE FROM screening;
DELETE FROM movie;
DELETE FROM theater;

-- 상영관 데이터 삽입
INSERT INTO theater (id, name, create_at, modify_at)
VALUES
    (1, '상영관 1관', NOW(), NOW()),
    (2, '상영관 2관', NOW(), NOW()),
    (3, '상영관 3관', NOW(), NOW());

-- movies 테이블에 데이터 삽입
INSERT INTO movie (id, name, thumbnail, genre, grade, release_date, running_time, create_at, modify_at)
WITH RECURSIVE cte (n) AS (
    SELECT 1
    UNION ALL
    SELECT n + 1 FROM cte WHERE n < 500 -- 생성할 영화 개수
)
SELECT
    n AS id,
    CONCAT('Movie ', LPAD(n, 3, '0')) AS name,
    NULL AS thumbnail,
    CASE MOD(n, 4)
        WHEN 0 THEN 'ACTION'
        WHEN 1 THEN 'ROMANCE'
        WHEN 2 THEN 'HORROR'
        WHEN 3 THEN 'SF'
        END AS genre,
    CASE MOD(n, 5)
        WHEN 0 THEN 'FROM_12_AGE'
        WHEN 1 THEN 'FROM_15_AGE'
        WHEN 2 THEN 'FROM_19_AGE'
        WHEN 3 THEN 'ALL_AGE'
        WHEN 4 THEN 'RESTRICTED'
        END AS grade,
    DATE_SUB(CURRENT_DATE, INTERVAL FLOOR(RAND() * 100 + n) DAY) AS release_date,
    MOD(n, 120) + 60 AS running_time,
    NOW() AS create_at,
    NOW() AS modify_at
FROM cte;

-- screenings 테이블에 상영 일정 데이터 삽입
INSERT INTO screening (movie_id, theater_id, start_at, end_at, create_at, modify_at)
WITH RECURSIVE cte (movie_id, schedule_number) AS (
    SELECT 1, 1
    UNION ALL
    SELECT
        CASE WHEN schedule_number = 6 THEN movie_id + 1 ELSE movie_id END,
        CASE WHEN schedule_number = 6 THEN 1 ELSE schedule_number + 1 END
    FROM cte
    WHERE movie_id <= 500 AND (schedule_number < 6 OR movie_id < 500)
)
SELECT
    movie_id,
    MOD(schedule_number - 1, 3) + 1 AS theater_id, -- 1~3 순환
    CASE
        WHEN MOD(schedule_number, 2) = 0 THEN DATE_ADD(CURRENT_DATE, INTERVAL (schedule_number * 3) HOUR)
        ELSE DATE_SUB(CURRENT_DATE, INTERVAL (schedule_number * 3) HOUR)
        END AS start_at,
    CASE
        WHEN MOD(schedule_number, 2) = 0 THEN DATE_ADD(CURRENT_DATE, INTERVAL (schedule_number * 3 + 2) HOUR)
        ELSE DATE_SUB(CURRENT_DATE, INTERVAL (schedule_number * 3 - 2) HOUR)
        END AS end_at,
    NOW() AS create_at,
    NOW() AS modify_at
FROM cte
WHERE schedule_number < 6
   OR movie_id < 500;
