CREATE DATABASE IF NOT EXISTS movies;

USE movies;

CREATE TABLE movie (
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
);

CREATE TABLE cinema (
    cinema_id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(197) NOT NULL,
    create_at datetime NOT NULL,
    creator varchar(197) NULL,
    update_at datetime NULL,
    updater varchar(197) NULL
);

CREATE TABLE seat (
    seat_id bigint AUTO_INCREMENT PRIMARY KEY,
    seat_row varchar(197) NOT NULL,
    seat_col varchar(197) NOT NULL,
    cinema_id bigint NOT NULL,
    create_at datetime NOT NULL,
    creator varchar(197) NULL,
    update_at datetime NULL,
    updater varchar(197) NULL
);

INSERT INTO cinema (`name`, `create_at`, `creator`)
VALUES
    ('A상영관', NOW(), 'lee'),
    ('B상영관', NOW(), 'lee'),
    ('C상영관', NOW(), 'lee'),
    ('D상영관', NOW(), 'lee'),
    ('E상영관', NOW(), 'lee'),
    ('F상영관', NOW(), 'lee'),
    ('G상영관', NOW(), 'lee'),
    ('H상영관', NOW(), 'lee');

insert into seat(`seat_row`, `seat_col`, `cinema_id`, `create_at`, `creator`) values
('A', '1', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('A', '2', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('A', '3', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('A', '4', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('A', '5', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('B', '1', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('B', '2', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('B', '3', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('B', '4', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('B', '5', (select cinema_id from cinema where name = 'A상영관'), now(), 'lee');

insert into movie(`title`, `film_ratings`, `release_date`, `thumbnail_image_path`, `running_time`, `cinema_id`, `create_at`, `creator`)
values
('범죄도시', 'MPAA_R_NC_17', '2017-10-03', 'https://i.namu.wiki/i/Y2IHkogjCdfvXyndMqlPxU2b171dMyUvhQG0VlXOOnkWWjb-_JDtDZHBqbj79Au0kTQeDPiKPMdbEMMcuxsdH6IJJvq0kXuve1F6X5CXkqg_fLhTN1f4Q8HYrBYryhlm3plgwZh783VyxUTYrXWBhw.webp', 121, (select cinema_id from cinema where name = 'A상영관'), now(), 'lee'),
('7광구', 'MPAA_PG_13R', '2011-08-04', 'https://i.namu.wiki/i/qy4zQm8MUuQkQoJk5_3nV6-DJurCRYpCvORjPT2kIJ1DB_MysYUs3QAGRe_izNCtni9SIu35F3RK4kGOSdW3v9Sdw9Hn1sW9QBjMm7Q6J4VPEEp-PAd3TCYFSiFtVOpAWR8xlYqkbtJkRzam2-IAaA.webp', 112, (select cinema_id from cinema where name = 'B상영관'), now(), 'lee'),
('7급공무원', 'MPAA_PG_13', '2009-04-22', 'https://i.namu.wiki/i/oQdTiAfjAkorQSkOQ3I4gMxSJAmlZT0nBiekNjP6-PyveNsTn-3nYhtLKbtJrOzU4XlPyT6F8t5Zn3w0-ILAubFGXT8L4YOxLDnyBEeP2mlntOIgIfGLVzMp9xX8DRL-LcKBoXRNRa2Aju0v7ADJwQ.webp', 112, (select cinema_id from cinema where name = 'C상영관'), now(), 'lee'),
('범죄도시2', 'MPAA_R_NC_17', '2017-10-03', 'https://i.namu.wiki/i/Y2IHkogjCdfvXyndMqlPxU2b171dMyUvhQG0VlXOOnkWWjb-_JDtDZHBqbj79Au0kTQeDPiKPMdbEMMcuxsdH6IJJvq0kXuve1F6X5CXkqg_fLhTN1f4Q8HYrBYryhlm3plgwZh783VyxUTYrXWBhw.webp', 106, (select cinema_id from cinema where name = 'D상영관'), now(), 'lee'),
('범죄도시3', 'MPAA_R_NC_17', '2023-05-31', 'https://i.namu.wiki/i/-1roayoUJ298nD4JBhmptGCBC6NXbfBUmlrC6BHWUWA_xtf3yDM8CqGoiEpM98nYP1QckoYl4aJrlR3wdqPgH7X9VH90g8EbnKwMiBGJYJ4Ch_XrHW4uLM570mnXFi_PyuGJb9Vxto_X2Xnt3MCZdw.webp', 105, (select cinema_id from cinema where name = 'E상영관'), now(), 'lee'),
('범죄도시4', 'MPAA_R_NC_17', '2024-02-23', 'https://i.namu.wiki/i/bJup5x2vQeG4ZPUdSvRoymfKo8niAGAO3NWtYNwFTLxhXzNfolnvkFT6aRIAiJ5mZERIH8HhU5who0y2paW0xzolK-GcEwM9GhzSoQ1glpV8d2CkttMEDPbSPMylRwMZHkTu4SbXznE7ou-gWk__Gg.webp', 109, (select cinema_id from cinema where name = 'F상영관'), now(), 'lee');

insert into movie_genre(`movie_id`, `name`, `create_at`, `creator`) values
((select movie_id from movie where title = '범죄도시'), '액션', now(), 'lee'),
((select movie_id from movie where title = '범죄도시2'), '액션', now(), 'lee'),
((select movie_id from movie where title = '범죄도시3'), '액션', now(), 'lee'),
((select movie_id from movie where title = '범죄도시4'), '액션', now(), 'lee'),
((select movie_id from movie where title = '7광구'), '스릴러', now(), 'lee'),
((select movie_id from movie where title = '7급공무원'), '코미디', now(), 'lee');

insert into screening_schedule(`start_time`, `end_time`, `movie_id`, `create_at`, `creator`) values
('2017-10:30 09:00', '2017-10:30 11:01', (select movie_id from movie where title = '범죄도시'), now(), 'lee'),
('2017-10:30 10:00', '2017-10:30 12:01', (select movie_id from movie where title = '범죄도시'), now(), 'lee'),
('2017-10:30 11:00', '2017-10:30 13:01', (select movie_id from movie where title = '범죄도시'), now(), 'lee'),
('2017-10:30 14:00', '2017-10:30 16:01', (select movie_id from movie where title = '범죄도시'), now(), 'lee');

