INSERT INTO upload_file (file_path, file_name, origin_file_name, created_by, created_at)
VALUES ('/radis/test/', 'test.png', 'origin.png', 99, NOW());

INSERT INTO movie (file_id, title, rating, release_date, running_time_minutes ,genre, created_by, created_at)
VALUES (1, '치토스', '2', '2025-01-01', 90, 'T', 99, NOW());

INSERT INTO movie (file_id, title, rating, release_date, running_time_minutes ,genre, created_by, created_at)
VALUES (1, '칸초', '1', '2025-01-02', 120, 'F', 99, NOW());

INSERT INTO movie (file_id, title, rating, release_date, running_time_minutes ,genre, created_by, created_at)
VALUES (1, '공공칠빵', '3', '2025-01-10', 110, 'A', 99, NOW());

INSERT INTO screen (screen_name, created_by, created_at)
VALUES ('1관', 99, NOW());

INSERT INTO screen (screen_name, created_by, created_at)
VALUES ('2관', 99, NOW());

INSERT INTO screening_schedule (movie_id, screen_id, show_start_at, created_by, created_at)
VALUES (1, 1, '2025-01-13 10:50:00', 99, NOW());

INSERT INTO screening_schedule (movie_id, screen_id, show_start_at, created_by, created_at)
VALUES (2, 2, '2025-01-13 12:00:00', 99, NOW());

INSERT INTO screening_schedule (movie_id, screen_id, show_start_at, created_by, created_at)
VALUES (3, 1, '2025-01-13 13:20:00', 99, NOW());