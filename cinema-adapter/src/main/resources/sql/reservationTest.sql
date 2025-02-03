INSERT INTO member (member_id, birth_date, created_by, created_at)
VALUES (1, '1999-01-01', 99, NOW());

INSERT INTO upload_file (file_id, file_path, file_name, origin_file_name, created_by, created_at)
VALUES (1, '/radis/test/', 'test1.png', 'origin.png', 99, NOW());

INSERT INTO upload_file (file_id, file_path, file_name, origin_file_name, created_by, created_at)
VALUES (2, '/radis/test/', 'test2.png', 'origin.png', 99, NOW());

INSERT INTO upload_file (file_id, file_path, file_name, origin_file_name, created_by, created_at)
VALUES (3, '/radis/test/', 'test3.png', 'origin.png', 99, NOW());

INSERT INTO movie (movie_id, file_id, title, rating, release_date, running_time_minutes ,genre, created_by, created_at)
VALUES (1, 1, '치토스', '2', '2024-12-11', 90, 'T', 99, NOW());

INSERT INTO movie (movie_id, file_id, title, rating, release_date, running_time_minutes ,genre, created_by, created_at)
VALUES (2, 2, '칸초', '1', '2024-11-17', 120, 'F', 99, NOW());

INSERT INTO movie (movie_id, file_id, title, rating, release_date, running_time_minutes ,genre, created_by, created_at)
VALUES (3, 3, '공공칠빵', '3', '2024-10-13', 110, 'A', 99, NOW());

INSERT INTO screen (screen_id, screen_name, created_by, created_at)
VALUES (1, '1관', 99, NOW());

INSERT INTO screen (screen_id, screen_name, created_by, created_at)
VALUES (2, '2관', 99, NOW());

INSERT INTO screening_schedule (schedule_id, movie_id, screen_id, show_start_at, created_by, created_at)
VALUES (1, 1, 1, '2025-02-13 10:50:00', 99, NOW());

INSERT INTO screening_schedule (schedule_id, movie_id, screen_id, show_start_at, created_by, created_at)
VALUES (2, 2, 2, '2025-02-20 12:00:00', 99, NOW());

INSERT INTO screening_schedule (schedule_id, movie_id, screen_id, show_start_at, created_by, created_at)
VALUES (3, 3, 1, '2025-02-17 13:20:00', 99, NOW());

INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (1, 1, 'A', 5, 99, NOW());
INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (2, 1, 'B', 5, 99, NOW());
INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (3, 1, 'C', 5, 99, NOW());
INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (4, 1, 'D', 5, 99, NOW());
INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (5, 1, 'E', 5, 99, NOW());

INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (6, 2, 'A', 5, 99, NOW());
INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (7, 2, 'B', 5, 99, NOW());
INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (8, 2, 'C', 5, 99, NOW());
INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (9, 2, 'D', 5, 99, NOW());
INSERT INTO screen_seat_layout (seat_layout_id, screen_id, seat_row, max_seat_number, created_by, created_at) VALUES (10, 2, 'E', 5, 99, NOW());

INSERT INTO ticket_reservation (ticket_id, schedule_id, screen_seat, member_id, created_by, created_at)
VALUES (1, 1, 'E01', 1, 99, NOW());