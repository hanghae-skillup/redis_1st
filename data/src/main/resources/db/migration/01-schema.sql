CREATE TABLE movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    rating VARCHAR(20) NOT NULL,
    release_date DATE NOT NULL,
    thumbnail_url VARCHAR(255) NOT NULL,
    running_time INT NOT NULL,
    genre VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);

CREATE TABLE theater (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);

CREATE TABLE seat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theater_id BIGINT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);

CREATE TABLE screening (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    theater_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);

CREATE TABLE reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    screening_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);
