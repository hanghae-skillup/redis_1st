CREATE DATABASE IF NOT EXISTS movies;

USE movies;

CREATE TABLE movies (
    movie_id bigint AUTO_INCREMENT PRIMARY KEY,
    title varchar(197) NOT NULL,
    film_ratings varchar(197) NOT NULL,
    release_date datetime NOT NULL,
    thumbnail_image_path varchar(255) NOT NULL,
    running_time bigint NOT NULL,
    cinema_id bigint NOT NULL,
    create_at datetime NOT NULL,
    creator varchar(197) NULL,
    update_at datetime NULL,
    updater varchar(197) NULL
);

CREATE TABLE movie_genre (
    movie_genre_id bigint AUTO_INCREMENT PRIMARY KEY,
    movie_id bigint NOT NULL,
    name varchar(197) NOT NULL,
    create_at datetime NOT NULL,
    creator varchar(197) NULL,
    update_at datetime NULL,
    updater varchar(197) NULL
);

CREATE TABLE screening_schedule(
    screening_schedule_id bigint AUTO_INCREMENT PRIMARY KEY,
    start_time datetime NOT NULL,
    end_time datetime NOT NULL,
    movie_id bigint NOT NULL,
    create_at datetime NOT NULL,
    creator varchar(197) NULL,
    update_at datetime NULL,
    updater varchar(197) NULL
)

CREATE TABLE cinema (
    cinema_id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(197) NOT NULL,
    create_at datetime NOT NULL,
    creator varchar(197) NULL,
    update_at datetime NULL,
    updater varchar(197) NULL
)

CREATE TABLE seat (
    seat_id bigint AUTO_INCREMENT PRIMARY KEY,
    row varchar(197) NOT NULL,
    col varchar(197) NOT NULL,
    cinema_id bigint NOT NULL,
    create_at datetime NOT NULL,
    creator varchar(197) NULL,
    update_at datetime NULL,
    updater varchar(197) NULL
)
