CREATE TABLE IF NOT EXISTS movie (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    grade VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    running_time INTEGER NOT NULL,
    release_date DATE NOT NULL,
    thumbnail_url VARCHAR(255),
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    INDEX idx_movie_title (title),
    INDEX idx_movie_genre (genre),
    INDEX idx_movie_release_date (release_date)
);

CREATE TABLE IF NOT EXISTS theater (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    UNIQUE INDEX uk_users_email (email)
);

CREATE TABLE IF NOT EXISTS seat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theater_id BIGINT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    seat_row VARCHAR(10) NOT NULL,
    seat_column INTEGER NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (theater_id) REFERENCES theater(id)
);

CREATE TABLE IF NOT EXISTS schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    start_at TIMESTAMP NOT NULL,
    end_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (theater_id) REFERENCES theater(id)
);

CREATE TABLE IF NOT EXISTS reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_number VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    schedule_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    UNIQUE INDEX uk_reservations_number (reservation_number),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (schedule_id) REFERENCES schedule(id),
    FOREIGN KEY (seat_id) REFERENCES seat(id)
); 