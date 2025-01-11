drop table IF EXISTS schedule;
drop table IF EXISTS movie;
drop table IF EXISTS rating;

CREATE TABLE IF NOT EXISTS rating (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS movie (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    genre VARCHAR(255),
    thumbnail_url VARCHAR(255),
    release_date DATE,
    runtime INTEGER,
    rating_id BIGINT
);

CREATE TABLE IF NOT EXISTS schedule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    screen_name VARCHAR(255),
    start_time DATETIME,
    end_time DATETIME,
    movie_id BIGINT
);