INSERT INTO upload_file (file_id, file_path, file_name, origin_file_name, created_by, created_at)
VALUES (1, '/radis/test/', 'test.png', 'origin.png', 99, NOW());

INSERT INTO movie (movie_id, file_id, title, rating, release_date, running_time_minutes ,genre, created_by, created_at)
VALUES (1, 1, '치토스', '2', '2025-01-01', 90, 'F', 99, NOW());

INSERT INTO screen (screen_id, screen_name, created_by, created_at)
VALUES (1, '1관', 99, NOW());
