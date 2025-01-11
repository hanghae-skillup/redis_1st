CREATE TABLE `genre` (
    `genre_id` INT NOT NULL AUTO_INCREMENT COMMENT '장르_ID',
    `genre_name` VARCHAR(50) NOT NULL COMMENT '장르명',
    `description` VARCHAR(200) NULL COMMENT '설명',
    `created_by` VARCHAR(20) NULL COMMENT '작성자',
    `created_at` DATETIME NULL COMMENT '작성일',
    `updated_by` VARCHAR(20) NULL COMMENT '수정자',
    `updated_at` DATETIME NULL COMMENT '수정일',
    PRIMARY KEY (`genre_id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `movie` (
    `movie_id` INT NOT NULL AUTO_INCREMENT COMMENT '영화_ID',
    `title` VARCHAR(100) NOT NULL COMMENT '영화제목',
    `rating` VARCHAR(50) NULL COMMENT '영상물 등급',
    `release_date` DATE NULL COMMENT '개봉일',
    `thumbnail_url` VARCHAR(255) NULL COMMENT '썸네일 이미지(URL)',
    `genre_id` INT NOT NULL COMMENT '장르_ID',
    `duration` INT NULL COMMENT '러닝 타임(분)',
    `created_by` VARCHAR(20) NULL COMMENT '작성자',
    `created_at` DATETIME NULL COMMENT '작성일',
    `updated_by` VARCHAR(20) NULL COMMENT '수정자',
    `updated_at` DATETIME NULL COMMENT '수정일',
    PRIMARY KEY (`movie_id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `user` (
    `user_id` INT NOT NULL AUTO_INCREMENT COMMENT '회원_ID',
    `user_name` VARCHAR(20) NOT NULL COMMENT '회원명',
    `created_by` VARCHAR(20) NULL COMMENT '작성자',
    `created_at` DATETIME NULL COMMENT '작성일',
    `updated_by` VARCHAR(20) NULL COMMENT '수정자',
    `updated_at` DATETIME NULL COMMENT '수정일',
    PRIMARY KEY (`user_id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `theater` (
    `theater_id` INT NOT NULL AUTO_INCREMENT COMMENT '상영관_ID',
    `theater_name` VARCHAR(20) NOT NULL COMMENT '상영관명',
    `location` VARCHAR(255) NULL COMMENT '위치',
    `created_by` VARCHAR(20) NULL COMMENT '작성자',
    `created_at` DATETIME NULL COMMENT '작성일',
    `updated_by` VARCHAR(20) NULL COMMENT '수정자',
    `updated_at` DATETIME NULL COMMENT '수정일',
    PRIMARY KEY (`theater_id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `showtime` (
    `showtime_id` INT NOT NULL AUTO_INCREMENT COMMENT '상영일정_ID',
    `movie_id` INT NOT NULL COMMENT '영화_ID',
    `theater_id` INT NOT NULL COMMENT '상영관_ID',
    `start_time` DATETIME NULL COMMENT '상영시작시간',
    `end_time` DATETIME NULL COMMENT '상영종료시간',
    `created_by` VARCHAR(20) NULL COMMENT '작성자',
    `created_at` DATETIME NULL COMMENT '작성일',
    `updated_by` VARCHAR(20) NULL COMMENT '수정자',
    `updated_at` DATETIME NULL COMMENT '수정일',
    PRIMARY KEY (`showtime_id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `seat` (
    `seat_id` INT NOT NULL AUTO_INCREMENT COMMENT '좌석_ID',
    `seat_name` VARCHAR(10) NOT NULL COMMENT '좌석명',
    `created_by` VARCHAR(20) NULL COMMENT '작성자',
    `created_at` DATETIME NULL COMMENT '작성일',
    `updated_by` VARCHAR(20) NULL COMMENT '수정자',
    `updated_at` DATETIME NULL COMMENT '수정일',
    PRIMARY KEY (`seat_id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `reservation` (
    `reservation_id` INT NOT NULL AUTO_INCREMENT COMMENT '예매_ID',
    `movie_id` INT NOT NULL COMMENT '영화_ID',
    `user_id` INT NOT NULL COMMENT '회원_ID',
    `showtime_id` INT NOT NULL COMMENT '상영일정_ID',
    `seat_id` INT NOT NULL COMMENT '좌석_ID',
    `created_by` VARCHAR(20) NULL COMMENT '작성자',
    `created_at` DATETIME NULL COMMENT '작성일',
    `updated_by` VARCHAR(20) NULL COMMENT '수정자',
    `updated_at` DATETIME NULL COMMENT '수정일',
    PRIMARY KEY (`reservation_id`)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
