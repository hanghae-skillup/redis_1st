
INSERT INTO movie (title, genre, thumbnail_url, release_date, runtime, rating_id) VALUES
('영화 제목 1', '드라마', 'https://example.com/thumbnail1.jpg', CURDATE() - INTERVAL 1 DAY, 121, 2),
('영화 제목 2', '액션', 'https://example.com/thumbnail2.jpg', CURDATE() - INTERVAL 2 DAY, 122, 1),
('영화 제목 3', '드라마', 'https://example.com/thumbnail3.jpg', CURDATE() - INTERVAL 3 DAY, 123, 2),
('영화 제목 4', '액션', 'https://example.com/thumbnail4.jpg', CURDATE() - INTERVAL 4 DAY, 124, 1),
('영화 제목 5', '드라마', 'https://example.com/thumbnail5.jpg', CURDATE() - INTERVAL 5 DAY, 125, 2);
