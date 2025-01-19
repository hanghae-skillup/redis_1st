-- 공통 컬럼
-- created_at DATETIME(6)
-- updated_at DATETIME(6)
-- created_by VARCHAR(255)
-- updated_by VARCHAR(255)

-- Movie 테이블
CREATE TABLE IF NOT EXISTS movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    grade VARCHAR(10) NOT NULL,
    release_date DATE NOT NULL,
    thumbnail_url VARCHAR(255),
    running_time INT,
    genre VARCHAR(50) NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Theater 테이블
CREATE TABLE IF NOT EXISTS theater (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Schedule 테이블
CREATE TABLE IF NOT EXISTS schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    start_time DATETIME(6) NOT NULL,
    end_time DATETIME(6) NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Seat 테이블
CREATE TABLE IF NOT EXISTS seat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_number VARCHAR(3) NOT NULL,
    theater_id BIGINT NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- 샘플 데이터 추가
INSERT INTO movie (title, grade, release_date, thumbnail_url, running_time, genre, created_at, updated_at, created_by, updated_by)
VALUES 
('웡카', '전체', '2024-01-31', 'https://example.com/wonka.jpg', 116, '판타지', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('시민덕희', '15세', '2024-01-24', 'https://example.com/deokhee.jpg', 114, '드라마', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('외계+인', '12세', '2024-01-10', 'https://example.com/alien.jpg', 142, 'SF', NOW(), NOW(), 'SYSTEM', 'SYSTEM');

INSERT INTO theater (name, created_at, updated_at, created_by, updated_by)
VALUES 
('1관', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('2관', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('3관', NOW(), NOW(), 'SYSTEM', 'SYSTEM');

INSERT INTO schedule (movie_id, theater_id, start_time, end_time, created_at, updated_at, created_by, updated_by)
VALUES 
(1, 1, '2024-01-19 10:00:00', '2024-01-19 12:00:00', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(2, 2, '2024-01-19 11:00:00', '2024-01-19 13:00:00', NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
(3, 3, '2024-01-19 12:00:00', '2024-01-19 14:30:00', NOW(), NOW(), 'SYSTEM', 'SYSTEM');

INSERT INTO seat (seat_number, theater_id, created_at, updated_at, created_by, updated_by)
VALUES 
('A1', 1, NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('A2', 1, NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('B1', 2, NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('B2', 2, NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('C1', 3, NOW(), NOW(), 'SYSTEM', 'SYSTEM'),
('C2', 3, NOW(), NOW(), 'SYSTEM', 'SYSTEM'); 