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

CREATE TABLE IF NOT EXISTS theater (
    theater_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '상영관 ID',
    name varchar(197) NOT NULL COMMENT '상영관 이름',
    create_at datetime NOT NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);

CREATE TABLE IF NOT EXISTS screening(
    screening_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '상영시간 ID',
    theater_id bigint unsigned NOT NULL COMMENT '상영관 ID',
    movie_id bigint unsigned NOT NULL COMMENT '영화 ID',
    start_time datetime NOT NULL COMMENT '시작 시간',
    end_time datetime NOT NULL COMMENT '종료 시간',
    create_at datetime NOT NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);

CREATE TABLE IF NOT EXISTS seat (
    seat_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '좌석 ID',
    theater_id bigint unsigned NOT NULL COMMENT '상영관 ID',
    seat_row varchar(197) NOT NULL COMMENT '좌석(행)',
    seat_col varchar(197) NOT NULL COMMENT '좌석(열)',
    create_at datetime NOT NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);

CREATE TABLE IF NOT EXISTS reserve (
    reserve_id bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '예약 ID',
    reserve_receipt_id varchar(197) NULL COMMENT '예약 영수증 ID',
    screening_id bigint unsigned NOT NULL COMMENT '상영시간 ID',
    seat_id bigint unsigned NOT NULL COMMENT '좌석 ID',
    user_id bigint unsigned NULL COMMENT '사용자 ID',
    create_at datetime NULL COMMENT '생성일',
    create_by varchar(197) NULL COMMENT '생성자',
    update_at datetime NULL COMMENT '수정일',
    update_by varchar(197) NULL COMMENT '수정자'
);