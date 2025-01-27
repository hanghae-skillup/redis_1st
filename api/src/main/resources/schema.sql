CREATE TABLE IF NOT EXISTS movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    grade VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    running_time INTEGER NOT NULL,
    release_date DATE NOT NULL,
    thumbnail_url VARCHAR(255),
    created_by VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_movie_title (title),
    INDEX idx_movie_genre (genre),
    INDEX idx_movie_release_date (release_date)
);

CREATE TABLE IF NOT EXISTS theater (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS seat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_number VARCHAR(10) NOT NULL,
    theater_id BIGINT NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_seat_theater (theater_id)
);

CREATE TABLE IF NOT EXISTS schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    start_at DATETIME NOT NULL,
    end_at DATETIME NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_schedule_start_at (start_at),
    INDEX idx_schedule_movie_theater (movie_id, theater_id)
); 