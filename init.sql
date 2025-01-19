CREATE TABLE IF NOT EXISTS movies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    rating ENUM('GENERAL', 'PG13', 'R', 'NC17') NOT NULL,
    release_date DATETIME NOT NULL,
    thumbnail_url VARCHAR(255) NOT NULL,
    running_time INT NOT NULL,
    genre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS screenings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    cinema_name VARCHAR(255) NOT NULL,
    start_time DATETIME NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);

-- Data Insertion
INSERT INTO movies (title, rating, release_date, thumbnail_url, running_time, genre)
SELECT
  CONCAT('Movie ', ROW_NUMBER() OVER (ORDER BY n.seq, n2.seq))       AS title,
  CASE (ROW_NUMBER() OVER (ORDER BY n.seq, n2.seq) - 1) % 4
      WHEN 0 THEN 'GENERAL'
      WHEN 1 THEN 'PG13'
      WHEN 2 THEN 'R'
      WHEN 3 THEN 'NC17'
  END                                                                 AS rating,
  DATE_ADD('2023-12-01 00:00:00',
           INTERVAL (ROW_NUMBER() OVER (ORDER BY n.seq, n2.seq) - 1) DAY
  )                                                                   AS release_date,
  CONCAT('http://example.com/',
         ROW_NUMBER() OVER (ORDER BY n.seq, n2.seq),
         '.jpg'
  )                                                                   AS thumbnail_url,
  90 + ROW_NUMBER() OVER (ORDER BY n.seq, n2.seq)                     AS running_time,
  CASE (ROW_NUMBER() OVER (ORDER BY n.seq, n2.seq) - 1) % 5
      WHEN 0 THEN 'Action'
      WHEN 1 THEN 'Drama'
      WHEN 2 THEN 'Thriller'
      WHEN 3 THEN 'Horror'
      WHEN 4 THEN 'Adventure'
      -- (필요하다면 WHEN 5 THEN 'Comedy' ... 더 늘려도 됨)
  END                                                                 AS genre
FROM (
  SELECT 0 AS seq
    UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
    UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
    UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
) n
CROSS JOIN (
  SELECT 0 AS seq
    UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
    UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
    UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
) n2
ORDER BY n.seq, n2.seq
LIMIT 100;


-- Insert 500 screenings dynamically
INSERT INTO screenings (movie_id, cinema_name, start_time)
SELECT
  m.id,
  CONCAT('Cinema ', FLOOR(1 + RAND() * 5)) AS cinema_name,
  DATE_ADD(
    m.release_date,
    INTERVAL (
      ROW_NUMBER() OVER (ORDER BY m.id)
      + 10 * n.seq
      + 100 * n2.seq
    ) HOUR
  ) AS start_time
FROM movies m
CROSS JOIN (
    SELECT 0 AS seq
    UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
    UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
    UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
) n
CROSS JOIN (
    SELECT 0 AS seq
    UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
    UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
    UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
) n2
ORDER BY RAND()
LIMIT 1000;
