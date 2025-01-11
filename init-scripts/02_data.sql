INSERT INTO `movie` (`running_time`, `created_at`, `id`, `modified_at`, `released_date`, `created_by`, `modified_by`, `thumbnail`, `title`, `firm_rating`, `genre`)
VALUES
  (120, '2025-01-01 12:00:00.000000', 1, '2025-01-01 12:00:00.000000', '2024-12-25 00:00:00.000000', 'admin', 'admin', 'thumbnail1.jpg', 'The Action Hero', 'AGE_15', 'ACTION'),
  (95, '2025-01-01 12:05:00.000000', 2, '2025-01-01 12:05:00.000000', '2024-11-01 00:00:00.000000', 'admin', 'admin', 'thumbnail2.jpg', 'Romantic Comedy', 'ALL', 'COMEDY'),
  (140, '2025-01-01 12:10:00.000000', 3, '2025-01-01 12:10:00.000000', '2024-10-15 00:00:00.000000', 'admin', 'admin', 'thumbnail3.jpg', 'Sci-Fi Thriller', 'AGE_15', 'SCI_FI');

INSERT INTO `theater` (`created_at`, `id`, `modified_at`, `created_by`, `modified_by`, `name`)
VALUES
  ('2025-01-01 13:00:00.000000', 1, '2025-01-01 13:00:00.000000', 'admin', 'admin', 'Main Theater'),
  ('2025-01-01 13:05:00.000000', 2, '2025-01-01 13:05:00.000000', 'admin', 'admin', 'Downtown Cinema'),
  ('2025-01-01 13:10:00.000000', 3, '2025-01-01 13:10:00.000000', 'admin', 'admin', 'Grand Multiplex');


INSERT INTO `screen` (`created_at`, `end_time`, `id`, `modified_at`, `movie_id`, `start_time`, `theater_id`, `created_by`, `modified_by`)
VALUES
  ('2025-01-01 14:00:00.000000', '2025-01-01 16:00:00.000000', 1, '2025-01-01 14:00:00.000000', 1, '2025-01-01 14:00:00.000000', 1, 'admin', 'admin'),
  ('2025-01-01 17:00:00.000000', '2025-01-01 18:35:00.000000', 2, '2025-01-01 17:00:00.000000', 2, '2025-01-01 17:00:00.000000', 2, 'admin', 'admin'),
  ('2025-01-01 19:00:00.000000', '2025-01-01 21:20:00.000000', 3, '2025-01-01 19:00:00.000000', 3, '2025-01-01 19:00:00.000000', 3, 'admin', 'admin');
