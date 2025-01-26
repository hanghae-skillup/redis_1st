-- Genre 데이터
INSERT INTO genre (name, description, created_by, created_at, updated_by, updated_at)
VALUES
    ('Action', 'High-energy movies with intense sequences', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Comedy', 'Humorous and lighthearted films', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Drama', 'Serious, character-driven narratives', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Science Fiction', 'Speculative and futuristic stories', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Horror', 'Movies designed to frighten and provoke fear', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Romance', 'Films exploring love and relationships', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Thriller', 'Suspenseful and exciting narratives', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Documentary', 'Non-fictional educational films', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Fantasy', 'Magical and imaginative worlds', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP),
    ('Animation', 'Animated feature films', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Theater 데이터
INSERT INTO theater (
    name,
    created_by,
    created_at,
    updated_by,
    updated_at
)
WITH RECURSIVE theater_names(name) AS (
    SELECT 'Regal Cinema'
    UNION ALL
    SELECT 'AMC Theatres'
    UNION ALL
    SELECT 'Cinemark'
    UNION ALL
    SELECT 'Landmark Theatres'
    UNION ALL
    SELECT 'Alamo Drafthouse'
    UNION ALL
    SELECT 'Showcase Cinemas'
    UNION ALL
    SELECT 'Carmike Cinemas'
    UNION ALL
    SELECT 'Marcus Theatres'
    UNION ALL
    SELECT 'Pacific Theatres'
    UNION ALL
    SELECT 'Harkins Theatres'
),
               city_prefixes(prefix) AS (
                   SELECT 'Downtown'
                   UNION ALL
                   SELECT 'Westside'
                   UNION ALL
                   SELECT 'Eastside'
                   UNION ALL
                   SELECT 'Northside'
                   UNION ALL
                   SELECT 'Southside'
                   UNION ALL
                   SELECT 'Central'
                   UNION ALL
                   SELECT 'Metro'
                   UNION ALL
                   SELECT 'Grand'
                   UNION ALL
                   SELECT 'Royal'
                   UNION ALL
                   SELECT 'Premium'
               ),
               generate_theaters(iteration, name) AS (
                   SELECT
                       1,
                       CONCAT(
                               (SELECT prefix FROM city_prefixes ORDER BY RAND() LIMIT 1),
            ' ',
            (SELECT name FROM theater_names ORDER BY RAND() LIMIT 1)
        )
                   UNION ALL
                   SELECT
                       iteration + 1,
                       CONCAT(
                               (SELECT prefix FROM city_prefixes ORDER BY RAND() LIMIT 1),
            ' ',
            (SELECT name FROM theater_names ORDER BY RAND() LIMIT 1)
        )
                   FROM generate_theaters
                   WHERE iteration < 500
               )

SELECT
    name,
    'system' AS created_by,
    CURRENT_TIMESTAMP AS created_at,
    'system' AS updated_by,
    CURRENT_TIMESTAMP AS updated_at
FROM generate_theaters
WHERE iteration > 1;

-- Movie 데이터
DROP TEMPORARY TABLE IF EXISTS base_movie_data;
CREATE TEMPORARY TABLE base_movie_data (
    base_title VARCHAR(100),
    primary_genre_id INT
);

-- Insert base movie titles
INSERT INTO base_movie_data (base_title, primary_genre_id) VALUES
-- Action
('Steel Resolve', 1),
('Borderline', 1),
('Thunder Strike', 1),
('Rogue Mission', 1),
('Horizon Zero', 1),

-- Comedy
('Laugh Track', 2),
('Perfect Mess', 2),
('Weekend Chaos', 2),
('Crazy Roommates', 2),
('Comedy Kings', 2),

-- Drama
('Broken Silence', 3),
('Distant Memories', 3),
('Silent Echoes', 3),
('Whispered Truth', 3),
('Autumn Leaves', 3),

-- Science Fiction
('Quantum Breach', 4),
('Synthetic Heart', 4),
('Digital Horizon', 4),
('Starlight Protocol', 4),
('Parallel Worlds', 4),

-- Horror
('Midnight Shadows', 5),
('Spectral Realm', 5),
('Whispers', 5),
('Dark Descent', 5),
('Nightmare Trigger', 5),

-- Romance
('First Bloom', 6),
('Heartstrings', 6),
('Moonlight Promise', 6),
('Seasons of Love', 6),
('Tender Moments', 6),

-- Thriller
('Silent Pursuit', 7),
('Coded Threat', 7),
('Blind Witness', 7),
('Shadow Protocol', 7),
('Critical Point', 7),

-- Documentary
('Unfiltered', 8),
('True Stories', 8),
('Life Unscripted', 8),
('Hidden Truths', 8),
('Global Perspectives', 8),

-- Fantasy
('Mystic Realm', 9),
('Dragon''s Legacy', 9),
('Ethereal Kingdom', 9),
('Dreamweaver', 9),
('Forgotten Magic', 9),

-- Animation
('Pixel Adventure', 10),
('Friendship Journey', 10),
('Colorful World', 10),
('Animated Hearts', 10),
('Playful Spirits', 10);

-- Temporary table for subtitle variations
DROP TEMPORARY TABLE IF EXISTS subtitle_variations;
CREATE TEMPORARY TABLE subtitle_variations (
    subtitle VARCHAR(50)
);

-- Insert subtitle variations
INSERT INTO subtitle_variations (subtitle) VALUES
                                               ('Director''s Cut'),
                                               ('Remastered'),
                                               ('Extended Edition'),
                                               ('Ultimate Version'),
                                               ('Special Edition'),
                                               ('Collector''s Edition'),
                                               ('Uncut Version'),
                                               ('Restored Version'),
                                               ('Anniversary Edition'),
                                               ('Definitive Cut');

-- Generate and insert movie data
INSERT INTO movie (
    title,
    rating,
    genre_id,
    release_date,
    running_time,
    thumbnail_url,
    created_by,
    created_at,
    updated_by,
    updated_at
)
WITH movie_variations AS (
    SELECT
        CONCAT(base_title, ' - ', subtitle) AS title,
        primary_genre_id AS genre_id,
        CASE
            WHEN RAND() < 0.2 THEN 'ALL'
            WHEN RAND() < 0.4 THEN 'TWELVE'
            WHEN RAND() < 0.6 THEN 'FIFTEEN'
            WHEN RAND() < 0.8 THEN 'ADULT'
            ELSE 'RESTRICTED'
            END AS rating,
        -- Generate realistic release dates
        DATE_SUB(CURRENT_TIMESTAMP, INTERVAL
            FLOOR(RAND() * (365 * 50)) DAY
        ) AS release_date,
        -- Varied running times
        FLOOR(80 + RAND() * 120) AS running_time,
        -- Generate thumbnail URL
        CONCAT('https://example.com/',
               LOWER(REPLACE(CONCAT(base_title, '-', subtitle), ' ', '-')),
               '.jpg'
        ) AS thumbnail_url
    FROM base_movie_data
             CROSS JOIN subtitle_variations
)
-- Insert generated movie data
SELECT
    title,
    rating,
    genre_id,
    release_date,
    running_time,
    thumbnail_url,
    'system' AS created_by,
    CURRENT_TIMESTAMP AS created_at,
    'system' AS updated_by,
    CURRENT_TIMESTAMP AS updated_at
FROM (
         SELECT
             title,
             rating,
             genre_id,
             release_date,
             running_time,
             thumbnail_url,
             ROW_NUMBER() OVER (PARTITION BY title ORDER BY RAND()) AS row_num
         FROM movie_variations
     ) AS numbered_movies
WHERE row_num = 1
LIMIT 500;

-- 예정된 상영 (SCHEDULED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
WITH RECURSIVE numbers AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 10000
)
SELECT
    MOD(n, 500) + 1,
    MOD(30, 5) + 1,
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL n HOUR),
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL (n + 2) HOUR),
    'SCHEDULED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM numbers;

-- 과거 완료된 상영 (COMPLETED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
WITH RECURSIVE numbers AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 10000
)
SELECT
    MOD(n, 500) + 1,
    MOD(n, 499) + 1,
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL -n HOUR),
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL (-n + 2) HOUR),
    'COMPLETED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM numbers;

-- 취소된 상영 (CANCELLED)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
WITH RECURSIVE numbers AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 5000
)
SELECT
    MOD(n, 500) + 1,
    MOD(n, 499) + 1,
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL n * 2 HOUR),
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL (n * 2 + 2) HOUR),
    'CANCELLED',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM numbers;

-- 진행중인 상영 (IN_PROGRESS)
INSERT INTO screening (movie_id, theater_id, screening_time, screening_end_time, status, created_by, created_at, updated_by, updated_at)
WITH RECURSIVE numbers AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM numbers WHERE n < 500
)
SELECT
    MOD(n, 500) + 1,
    MOD(n, 499) + 1,
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL -30 MINUTE),
    DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 90 MINUTE),
    'IN_PROGRESS',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
FROM numbers;

INSERT INTO seat (theater_id, row_val, col_val, created_by, created_at, updated_by, updated_at)
SELECT t.theater_id AS theater_id,
       r.letter AS row_val,
       c.num    AS col_val,
       'system',
       CURRENT_TIMESTAMP,
       'system',
       CURRENT_TIMESTAMP
FROM theater t
         /* row(A~E) 생성 */
         CROSS JOIN (
    SELECT 'A' AS letter
    UNION SELECT 'B'
    UNION SELECT 'C'
    UNION SELECT 'D'
    UNION SELECT 'E'
) r
    /* col(1~5) 생성 */
         CROSS JOIN (
    SELECT 1 AS num
    UNION SELECT 2
    UNION SELECT 3
    UNION SELECT 4
    UNION SELECT 5
) c;