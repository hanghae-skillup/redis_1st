DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS theater;
DROP TABLE IF EXISTS screening;
DROP TABLE IF EXISTS seat;
DROP TABLE IF EXISTS reservation;

CREATE TABLE IF NOT EXISTS genre (
    genre_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS movie (
    movie_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    rating VARCHAR(20) NOT NULL,
    genre_id INT UNSIGNED,
    release_date DATE NOT NULL,
    thumbnail_url VARCHAR(200),
    running_time INTEGER NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS theater (
    theater_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS screening (
    screening_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    movie_id INT UNSIGNED,
    theater_id INT UNSIGNED,
    screening_time TIMESTAMP NOT NULL,
    screening_end_time TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS seat (
    seat_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    theater_id INT UNSIGNED NOT NULL,
    row_val VARCHAR(10) NOT NULL,
    col_val INT NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS reservation (
    reservation_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    seat_id INT UNSIGNED NOT NULL,
    screening_id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    reservation_time DATETIME NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

SET cte_max_recursion_depth = 20000;