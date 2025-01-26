DROP TABLE IF EXISTS cinema.movie_schedule;
DROP TABLE IF EXISTS cinema.seat;
DROP TABLE IF EXISTS cinema.theater;
DROP TABLE IF EXISTS cinema.movie;
DROP TABLE IF EXISTS cinema.cinema;

CREATE TABLE cinema.cinema (
                        id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        contact_number VARCHAR(255) NOT NULL
);

CREATE TABLE cinema.movie (
                       id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       genre VARCHAR(50) NOT NULL,
                       movie_rating VARCHAR(20) NOT NULL,
                       release_date DATE NOT NULL,
                       running_time_minutes INT NOT NULL,
                       thumbnail_url VARCHAR(255) NOT NULL
);

CREATE TABLE cinema.theater (
                         id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         cinema_id BIGINT UNSIGNED NOT NULL
);

CREATE TABLE cinema.movie_schedule (
                                id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                                start_at DATETIME NOT NULL,
                                end_at DATETIME NOT NULL,
                                movie_id BIGINT UNSIGNED NOT NULL,
                                theater_id BIGINT UNSIGNED NOT NULL,
                                cinema_id BIGINT UNSIGNED NOT NULL
);

CREATE TABLE cinema.seat(
                            id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                            theater_id BIGINT UNSIGNED NOT NULL,
                            position_row VARCHAR(255) NOT NULL,
                            position_column VARCHAR(255) NOT NULL
)