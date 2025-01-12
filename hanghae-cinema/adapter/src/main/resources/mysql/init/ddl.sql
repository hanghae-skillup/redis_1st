-- Create movies table
CREATE TABLE movies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(50),
    release_date DATE,
    runtime_minutes INT NOT NULL,
    age_rating VARCHAR(50),
    thumbnail_url VARCHAR(500),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Create screenings table
CREATE TABLE screenings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater VARCHAR(255) NOT NULL,
    show_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

-- Create screening_schedules table
CREATE TABLE screening_schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    screening_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    FOREIGN KEY (screening_id) REFERENCES screenings(id)
);

-- Create reservations table
CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    screening_id BIGINT NOT NULL,
    user_id VARCHAR(255),
    seat_number VARCHAR(5) NOT NULL,
    reserved_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    FOREIGN KEY (screening_id) REFERENCES screenings(id),
    UNIQUE KEY uk_screening_seat (screening_id, seat_number)
);
