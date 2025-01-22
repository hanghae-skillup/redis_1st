INSERT INTO movie (title, grade, genre, running_time, release_date, thumbnail_url, created_by, created_at, updated_by, updated_at)
VALUES ('웡카', '전체관람가', '판타지', 116, '2024-01-31', 'https://example.com/wonka.jpg', 'SYSTEM', NOW(), 'SYSTEM', NOW());

INSERT INTO theater (name, created_by, created_at, updated_by, updated_at)
VALUES ('1관', 'SYSTEM', NOW(), 'SYSTEM', NOW()),
       ('2관', 'SYSTEM', NOW(), 'SYSTEM', NOW());

INSERT INTO schedule (movie_id, theater_id, start_at, end_at, created_by, created_at, updated_by, updated_at)
VALUES (1, 1, '2024-02-14 10:00:00', '2024-02-14 12:00:00', 'SYSTEM', NOW(), 'SYSTEM', NOW()),
       (1, 2, '2024-02-14 11:00:00', '2024-02-14 13:00:00', 'SYSTEM', NOW(), 'SYSTEM', NOW()); 