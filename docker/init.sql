CREATE DATABASE IF NOT EXISTS movies;

USE movies;

CREATE TABLE IF NOT EXISTS movie (
    movie_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT 'movie ID',
    title varchar(197) NOT NULL COMMENT '영화 제목',
    film_ratings varchar(197) NOT NULL COMMENT '영화 등급',
    release_date datetime NOT NULL COMMENT '개봉일',
    thumbnail_image_path varchar(255) NOT NULL COMMENT '썸네일 이미지 경로',
    running_time bigint NOT NULL COMMENT '상영시간(분단위)',
    create_at datetime NOT NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);

CREATE TABLE IF NOT EXISTS movie_genre (
    movie_genre_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '영화 장르 ID',
    movie_id bigint unsigned NOT NULL COMMENT '영화 ID',
    name varchar(197) NOT NULL COMMENT '장르명',
    create_at datetime NOT NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);

CREATE TABLE IF NOT EXISTS movie_theater (
    movie_theater_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '영화 - 상영관 매핑 ID',
    movie_id bigint unsigned NOT NULL COMMENT '영화 ID',
    theater_id bigint unsigned NOT NULL COMMENT '상영관 ID',
    create_at datetime NOT NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);

CREATE TABLE IF NOT EXISTS theater (
    theater_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '상영관 ID',
    name varchar(197) NOT NULL COMMENT '상영관 이름',
    create_at datetime NOT NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);

CREATE TABLE IF NOT EXISTS screening_schedule(
    screening_schedule_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '상영 시간표 ID',
    movie_theater_id bigint unsigned NOT NULL COMMENT '영화 - 상영관 매핑 ID',
    start_time datetime NOT NULL COMMENT '시작 시간',
    end_time datetime NOT NULL COMMENT '종료 시간',
    create_at datetime NOT NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);

CREATE TABLE seat (
    seat_id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
    theater_id bigint unsigned NOT NULL,
    seat_row varchar(197) NOT NULL,
    seat_col varchar(197) NOT NULL,
    create_at datetime NOT NULL,
    create_by varchar(197) NULL,
    update_at datetime NULL,
    update_by varchar(197) NULL
);

INSERT INTO theater (`name`, `create_at`, `create_by`)
VALUES
    ('A상영관', NOW(), 'lee'),
    ('B상영관', NOW(), 'lee'),
    ('C상영관', NOW(), 'lee'),
    ('D상영관', NOW(), 'lee'),
    ('E상영관', NOW(), 'lee'),
    ('F상영관', NOW(), 'lee'),
    ('G상영관', NOW(), 'lee'),
    ('H상영관', NOW(), 'lee');

INSERT INTO seat (`seat_row`, `seat_col`, `theater_id`, `create_at`, `create_by`)
VALUES
    ('A', '1', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('A', '2', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('A', '3', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('A', '4', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('A', '5', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('B', '1', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('B', '2', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('B', '3', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('B', '4', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('B', '5', (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ('A', '1', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('A', '2', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('A', '3', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('A', '4', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('A', '5', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('B', '1', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('B', '2', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('B', '3', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('B', '4', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee'),
    ('B', '5', (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee');

INSERT INTO movie (`title`, `film_ratings`, `release_date`, `thumbnail_image_path`, `running_time`, `create_at`, `create_by`)
VALUES
    ('범죄도시', 'MPAA_R_NC_17', '2017-10-03', 'https://example.com/image1.webp', 121, NOW(), 'lee'),
    ('7광구', 'MPAA_PG_13R', '2011-08-04', 'https://example.com/image2.webp', 112, NOW(), 'lee'),
    ('7급공무원', 'MPAA_PG_13', '2009-04-22', 'https://example.com/image3.webp', 112, NOW(), 'lee'),
    ('범죄도시2', 'MPAA_R_NC_17', '2017-10-03', 'https://example.com/image4.webp', 106, NOW(), 'lee'),
    ('범죄도시3', 'MPAA_R_NC_17', '2023-05-31', 'https://example.com/image5.webp', 105, NOW(), 'lee'),
    ('범죄도시4', 'MPAA_R_NC_17', '2024-02-23', 'https://example.com/image6.webp', 109, NOW(), 'lee');

INSERT INTO movie_genre (`movie_id`, `name`, `create_at`, `create_by`)
VALUES
    ((SELECT movie_id FROM movie WHERE title = '범죄도시'), '액션', NOW(), 'lee'),
    ((SELECT movie_id FROM movie WHERE title = '범죄도시2'), '액션', NOW(), 'lee'),
    ((SELECT movie_id FROM movie WHERE title = '범죄도시3'), '액션', NOW(), 'lee'),
    ((SELECT movie_id FROM movie WHERE title = '범죄도시4'), '액션', NOW(), 'lee'),
    ((SELECT movie_id FROM movie WHERE title = '7광구'), '스릴러', NOW(), 'lee'),
    ((SELECT movie_id FROM movie WHERE title = '7급공무원'), '코미디', NOW(), 'lee');

INSERT INTO movie_theater (`movie_id`, `theater_id`, `create_at`, `create_by`)
    VALUES
    ((SELECT movie_id FROM movie WHERE title = '범죄도시'), (SELECT theater_id FROM theater WHERE name = 'A상영관'), NOW(), 'lee'),
    ((SELECT movie_id FROM movie WHERE title = '범죄도시2'), (SELECT theater_id FROM theater WHERE name = 'B상영관'), NOW(), 'lee');

INSERT INTO screening_schedule (`start_time`, `end_time`, `movie_theater_id`, `create_at`, `create_by`)
VALUES
    ('2017-10-30 09:00', '2017-10-30 11:01', (SELECT movie_theater_id FROM movie_theater WHERE movie_id = (SELECT movie_id FROM movie WHERE title = '범죄도시')), NOW(), 'lee'),
    ('2017-10-30 10:00', '2017-10-30 12:01', (SELECT movie_theater_id FROM movie_theater WHERE movie_id = (SELECT movie_id FROM movie WHERE title = '범죄도시')), NOW(), 'lee'),
    ('2017-10-30 11:00', '2017-10-30 13:01', (SELECT movie_theater_id FROM movie_theater WHERE movie_id = (SELECT movie_id FROM movie WHERE title = '범죄도시')), NOW(), 'lee'),
    ('2017-10-30 14:00', '2017-10-30 16:01', (SELECT movie_theater_id FROM movie_theater WHERE movie_id = (SELECT movie_id FROM movie WHERE title = '범죄도시')), NOW(), 'lee');

