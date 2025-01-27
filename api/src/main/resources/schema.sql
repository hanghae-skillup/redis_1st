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

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at DATETIME NOT NULL,
    INDEX idx_user_email (email)
);

CREATE TABLE IF NOT EXISTS seat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_number VARCHAR(10) NOT NULL,
    theater_id BIGINT NOT NULL,
    row_number INT NOT NULL,
    column_number INT NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (theater_id) REFERENCES theater(id),
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
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (theater_id) REFERENCES theater(id),
    INDEX idx_schedule_start_at (start_at),
    INDEX idx_schedule_movie_theater (movie_id, theater_id)
);

CREATE TABLE IF NOT EXISTS reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    schedule_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    reservation_number VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    reserved_at DATETIME NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    created_by VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (schedule_id) REFERENCES schedule(id),
    FOREIGN KEY (seat_id) REFERENCES seat(id),
    INDEX idx_reservation_user (user_id),
    INDEX idx_reservation_schedule (schedule_id),
    UNIQUE INDEX idx_reservation_number (reservation_number)
); 