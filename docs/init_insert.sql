INSERT INTO upload_file (file_path, file_name, origin_file_name, created_by, created_at)
VALUES ('/radis/test/', 'test.png', 'origin.png', 99, NOW());

INSERT INTO movie (file_id, title, rating_id, release_date, running_time ,genre_id, created_by, created_at)
VALUES (1, '치토스', '12', '2025-01-01', 90, 'family', 99, NOW());

INSERT INTO movie (file_id, title, rating_id, release_date, running_time ,genre_id, created_by, created_at)
VALUES (1, '칸초', 'all', '2025-01-02', 120, 'family', 99, NOW());

INSERT INTO movie (file_id, title, rating_id, release_date, running_time ,genre_id, created_by, created_at)
VALUES (1, '공공칠빵', '15', '2025-01-10', 110, 'action', 99, NOW());

INSERT INTO screen (screen_name, created_by, created_at)
VALUES ('1관', 99, NOW());

INSERT INTO screen (screen_name, created_by, created_at)
VALUES ('2관', 99, NOW());

INSERT INTO screening_schedule (movie_id, screen_id, show_start_datetime, created_by, created_at)
VALUES (1, 1, '2025-01-13 10:50:00', 99, NOW());

INSERT INTO screening_schedule (movie_id, screen_id, show_start_datetime, created_by, created_at)
VALUES (2, 2, '2025-01-13 12:00:00', 99, NOW());

INSERT INTO screening_schedule (movie_id, screen_id, show_start_datetime, created_by, created_at)
VALUES (3, 1, '2025-01-13 13:20:00', 99, NOW());