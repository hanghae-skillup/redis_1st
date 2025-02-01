SET SESSION cte_max_recursion_depth = 1000000;

DELETE FROM screening;
DELETE FROM movie;
DELETE FROM theater;
DELETE FROM user;
DELETE FROM reservation;

-- 상영관 데이터 삽입
INSERT INTO theater (id, name, create_at, modify_at)
VALUES
    (1, '상영관 1관', NOW(), NOW()),
    (2, '상영관 2관', NOW(), NOW()),
    (3, '상영관 3관', NOW(), NOW());


INSERT INTO user (id, name, create_at, modify_at)
VALUES
    (1, '유저 1', NOW(), NOW()),
    (2, '유저 2', NOW(), NOW()),
    (3, '유저 3', NOW(), NOW()),
    (4, '유저 4', NOW(), NOW()),
    (5, '유저 5', NOW(), NOW()),
    (6, '유저 6', NOW(), NOW()),
    (7, '유저 7', NOW(), NOW()),
    (8, '유저 8', NOW(), NOW()),
    (9, '유저 9', NOW(), NOW()),
    (10, '유저 10', NOW(), NOW()),
    (11, '유저 11', NOW(), NOW()),
    (12, '유저 12', NOW(), NOW()),
    (13, '유저 13', NOW(), NOW()),
    (14, '유저 14', NOW(), NOW()),
    (15, '유저 15', NOW(), NOW()),
    (16, '유저 16', NOW(), NOW()),
    (17, '유저 17', NOW(), NOW()),
    (18, '유저 18', NOW(), NOW()),
    (19, '유저 19', NOW(), NOW()),
    (20, '유저 20', NOW(), NOW()),
    (21, '유저 21', NOW(), NOW()),
    (22, '유저 22', NOW(), NOW()),
    (23, '유저 23', NOW(), NOW()),
    (24, '유저 24', NOW(), NOW()),
    (25, '유저 25', NOW(), NOW()),
    (26, '유저 26', NOW(), NOW()),
    (27, '유저 27', NOW(), NOW()),
    (28, '유저 28', NOW(), NOW()),
    (29, '유저 29', NOW(), NOW()),
    (30, '유저 30', NOW(), NOW()),
    (31, '유저 31', NOW(), NOW()),
    (32, '유저 32', NOW(), NOW()),
    (33, '유저 33', NOW(), NOW()),
    (34, '유저 34', NOW(), NOW()),
    (35, '유저 35', NOW(), NOW()),
    (36, '유저 36', NOW(), NOW()),
    (37, '유저 37', NOW(), NOW()),
    (38, '유저 38', NOW(), NOW()),
    (39, '유저 39', NOW(), NOW()),
    (40, '유저 40', NOW(), NOW()),
    (41, '유저 41', NOW(), NOW()),
    (42, '유저 42', NOW(), NOW()),
    (43, '유저 43', NOW(), NOW()),
    (44, '유저 44', NOW(), NOW()),
    (45, '유저 45', NOW(), NOW()),
    (46, '유저 46', NOW(), NOW()),
    (47, '유저 47', NOW(), NOW()),
    (48, '유저 48', NOW(), NOW()),
    (49, '유저 49', NOW(), NOW()),
    (50, '유저 50', NOW(), NOW()),
    (51, '유저 51', NOW(), NOW()),
    (52, '유저 52', NOW(), NOW()),
    (53, '유저 53', NOW(), NOW()),
    (54, '유저 54', NOW(), NOW()),
    (55, '유저 55', NOW(), NOW()),
    (56, '유저 56', NOW(), NOW()),
    (57, '유저 57', NOW(), NOW()),
    (58, '유저 58', NOW(), NOW()),
    (59, '유저 59', NOW(), NOW()),
    (60, '유저 60', NOW(), NOW()),
    (61, '유저 61', NOW(), NOW()),
    (62, '유저 62', NOW(), NOW()),
    (63, '유저 63', NOW(), NOW()),
    (64, '유저 64', NOW(), NOW()),
    (65, '유저 65', NOW(), NOW()),
    (66, '유저 66', NOW(), NOW()),
    (67, '유저 67', NOW(), NOW()),
    (68, '유저 68', NOW(), NOW()),
    (69, '유저 69', NOW(), NOW()),
    (70, '유저 70', NOW(), NOW()),
    (71, '유저 71', NOW(), NOW()),
    (72, '유저 72', NOW(), NOW()),
    (73, '유저 73', NOW(), NOW()),
    (74, '유저 74', NOW(), NOW()),
    (75, '유저 75', NOW(), NOW()),
    (76, '유저 76', NOW(), NOW()),
    (77, '유저 77', NOW(), NOW()),
    (78, '유저 78', NOW(), NOW()),
    (79, '유저 79', NOW(), NOW()),
    (80, '유저 80', NOW(), NOW()),
    (81, '유저 81', NOW(), NOW()),
    (82, '유저 82', NOW(), NOW()),
    (83, '유저 83', NOW(), NOW()),
    (84, '유저 84', NOW(), NOW()),
    (85, '유저 85', NOW(), NOW()),
    (86, '유저 86', NOW(), NOW()),
    (87, '유저 87', NOW(), NOW()),
    (88, '유저 88', NOW(), NOW()),
    (89, '유저 89', NOW(), NOW()),
    (90, '유저 90', NOW(), NOW()),
    (91, '유저 91', NOW(), NOW()),
    (92, '유저 92', NOW(), NOW()),
    (93, '유저 93', NOW(), NOW()),
    (94, '유저 94', NOW(), NOW()),
    (95, '유저 95', NOW(), NOW()),
    (96, '유저 96', NOW(), NOW()),
    (97, '유저 97', NOW(), NOW()),
    (98, '유저 98', NOW(), NOW()),
    (99, '유저 99', NOW(), NOW()),
    (100, '유저 100', NOW(), NOW());

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

INSERT INTO seat (id, theater_id, position, create_at, modify_at)
WITH RECURSIVE seat_cte (seat_id, theater_id, row_name, col_number) AS (
    -- 초기 값: 첫 좌석 데이터
    SELECT
        1 AS seat_id,      -- 좌석 ID 시작값
        1 AS theater_id,   -- 상영관 ID 시작값
        'A' AS row_name,   -- 첫 행
        1 AS col_number    -- 첫 열
    UNION ALL
    -- 재귀적으로 다음 좌석을 생성
    SELECT
            seat_id + 1,                                                   -- 다음 좌석 ID
            CASE WHEN col_number = 5 AND row_name = 'E' THEN theater_id + 1  -- 마지막 열, 마지막 행이면 다음 상영관
                 ELSE theater_id                                            -- 그렇지 않으면 같은 상영관
                END,
            CASE WHEN col_number = 5 THEN                                   -- 마지막 열이면
                     CASE WHEN row_name = 'E' THEN 'A'                         -- 마지막 행이면 첫 행으로
                          ELSE CHAR(ASCII(row_name) + 1)                       -- 다음 행
                         END
                 ELSE row_name                                             -- 같은 행 유지
                END,
            CASE WHEN col_number = 5 THEN 1                                -- 마지막 열이면 첫 열로
                 ELSE col_number + 1                                       -- 다음 열
                END
    FROM seat_cte
    WHERE theater_id <= 3 -- 상영관 3개까지만 생성
)
-- 실제 데이터 삽입
SELECT
    seat_id,                                               -- 좌석 ID
    theater_id,                                            -- 상영관 ID
    CONCAT(row_name, col_number) AS position,             -- 좌석 번호 (예: A1, B3 등)
    NOW() AS create_at,                                   -- 생성 시각
    NOW() AS modify_at                                    -- 수정 시각
FROM seat_cte;