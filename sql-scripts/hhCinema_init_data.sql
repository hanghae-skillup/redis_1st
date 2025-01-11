-- Genre 데이터 삽입(8개)
INSERT INTO `genre` (genre_name, description, created_by, created_at)
VALUES
('액션', '긴장감 넘치는 액션 영화', 'admin', NOW()),
('코미디', '웃음을 주는 영화', 'admin', NOW()),
('드라마', '감동적인 드라마', 'admin', NOW()),
('스릴러', '스릴 넘치는 영화', 'admin', NOW()),
('SF', '공상과학 영화', 'admin', NOW()),
('판타지', '환상적인 세계관을 그린 영화', 'admin', NOW()),
('로맨스', '사랑과 감정을 다룬 영화', 'admin', NOW()),
('공포', '두려움을 느끼게 하는 영화', 'admin', NOW());

-- Movie 데이터 삽입(9개)
INSERT INTO `movie` (title, rating, release_date, thumbnail_url, genre_id, duration, created_by, created_at)
VALUES
('범죄도시 3', '15세 관람가', '2025-01-01', 'http://example.com/thumbnail6.jpg', 1, 120, 'admin', NOW()),
('극한직업', '12세 관람가', '2025-01-02', 'http://example.com/thumbnail7.jpg', 2, 110, 'admin', NOW()),
('국제시장', '전체 관람가', '2025-01-03', 'http://example.com/thumbnail8.jpg', 3, 126, 'admin', NOW()),
('탑건: 매버릭', '12세 관람가', '2025-01-04', 'http://example.com/thumbnail9.jpg', 1, 130, 'admin', NOW()),
('기생충', '청소년 관람불가', '2025-01-05', 'http://example.com/thumbnail10.jpg', 4, 131, 'admin', NOW()),
('어벤져스: 엔드게임', '12세 관람가', '2025-01-06', 'http://example.com/thumbnail11.jpg', 5, 181, 'admin', NOW()),
('반지의 제왕', '12세 관람가', '2025-01-07', 'http://example.com/thumbnail12.jpg', 6, 200, 'admin', NOW()),
('라라랜드', '15세 관람가', '2025-01-08', 'http://example.com/thumbnail13.jpg', 7, 128, 'admin', NOW()),
('컨저링', '청소년 관람불가', '2025-01-09', 'http://example.com/thumbnail14.jpg', 8, 112, 'admin', NOW());

-- User 데이터 삽입(10개)
INSERT INTO `user` (user_name, created_by, created_at)
VALUES
('김영희', 'admin', NOW()),
('박철수', 'admin', NOW()),
('이민호', 'admin', NOW()),
('한지민', 'admin', NOW()),
('정우성', 'admin', NOW()),
('김혜수', 'admin', NOW()),
('이준기', 'admin', NOW()),
('송중기', 'admin', NOW()),
('유재석', 'admin', NOW()),
('강호동', 'admin', NOW());

-- Theater 데이터 삽입(10개)
INSERT INTO `theater` (theater_name, location, created_by, created_at)
VALUES
('강남CGV', '서울 강남구 논현동', 'admin', NOW()),
('월드타워CGV', '서울 송파구 신천동', 'admin', NOW()),
('코엑스CGV', '서울 강남구 삼성동', 'admin', NOW()),
('용산CGV', '서울 용산구 한강로동', 'admin', NOW()),
('건대입구CGV', '서울 광진구 화양동', 'admin', NOW()),
('홍대CGV', '서울 마포구 서교동', 'admin', NOW()),
('신촌CGV', '서울 서대문구 창천동', 'admin', NOW()),
('노원CGV', '서울 노원구 상계동', 'admin', NOW()),
('은평CGV', '서울 은평구 진관동', 'admin', NOW()),
('천호CGV', '서울 강동구 천호동', 'admin', NOW());

-- Seat 데이터 삽입(상영관별 5x5 좌석 데이터)
INSERT INTO `seat` (seat_name, created_by, created_at)
VALUES
('A1', 'admin', NOW()), ('A2', 'admin', NOW()), ('A3', 'admin', NOW()), ('A4', 'admin', NOW()), ('A5', 'admin', NOW()),
('B1', 'admin', NOW()), ('B2', 'admin', NOW()), ('B3', 'admin', NOW()), ('B4', 'admin', NOW()), ('B5', 'admin', NOW()),
('C1', 'admin', NOW()), ('C2', 'admin', NOW()), ('C3', 'admin', NOW()), ('C4', 'admin', NOW()), ('C5', 'admin', NOW()),
('D1', 'admin', NOW()), ('D2', 'admin', NOW()), ('D3', 'admin', NOW()), ('D4', 'admin', NOW()), ('D5', 'admin', NOW()),
('E1', 'admin', NOW()), ('E2', 'admin', NOW()), ('E3', 'admin', NOW()), ('E4', 'admin', NOW()), ('E5', 'admin', NOW());