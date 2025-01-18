-- Movie 테이블
CREATE TABLE IF NOT EXISTS movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    grade VARCHAR(10) NOT NULL,
    release_date DATE NOT NULL,
    thumbnail_url VARCHAR(255),
    running_time INT,
    genre VARCHAR(50) NOT NULL
);

-- Theater 테이블
CREATE TABLE IF NOT EXISTS theater (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Schedule 테이블
CREATE TABLE IF NOT EXISTS schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    start_time DATETIME(6) NOT NULL,
    end_time DATETIME(6) NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (theater_id) REFERENCES theater(id)
);

-- Seat 테이블
CREATE TABLE IF NOT EXISTS seat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_number VARCHAR(3) NOT NULL,
    theater_id BIGINT NOT NULL,
    FOREIGN KEY (theater_id) REFERENCES theater(id)
); 