CREATE DATABASE `my` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `my`;

CREATE TABLE `tb_movie` (
    `movie_id` int unsigned NOT NULL AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `description` varchar(1000) DEFAULT NULL,
    `status` varchar(20) NOT NULL COMMENT '영화 상태 (상영중, 상영종료, 상영예정)',
    `rating` varchar(20) NOT NULL,
    `genre` varchar(20) NOT NULL,
    `thumbnail` varchar(255) DEFAULT NULL COMMENT '썸네일 url',
    `running_time` smallint NOT NULL COMMENT '영화시간',
    `release_date` date NOT NULL,
    `updated_at` datetime NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_movie_showtime` (
    `showtime_id` int unsigned NOT NULL AUTO_INCREMENT,
    `movie_id` int unsigned NOT NULL,
    `start` time NOT NULL COMMENT '상영시작시간',
    `end` time NOT NULL COMMENT '상영종료시간',
    `updated_at` datetime NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`showtime_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_movie_theater_rel` (
    `movie_theater_rel_id` int unsigned NOT NULL AUTO_INCREMENT,
    `movie_id` int unsigned NOT NULL,
    `theater_id` int unsigned NOT NULL,
    `updated_at` datetime NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`movie_theater_rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_theater` (
    `theater_id` int unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `updated_at` datetime NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`theater_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `my`.`tb_movie`
    ADD INDEX `idx_genre_title_release_date` (`genre` ASC, `title` ASC, `release_date` DESC) VISIBLE,
ADD INDEX `idx_genre` (`genre` ASC) VISIBLE,
ADD INDEX `idx_title` (`title` ASC) VISIBLE;
;


ALTER TABLE `my`.`tb_movie_showtime`
    ADD INDEX `idx_movie_id` (`movie_id` ASC) VISIBLE;
;

ALTER TABLE `my`.`tb_movie_theater_rel`
    ADD INDEX `idx_movie_id` (`movie_id` ASC) VISIBLE;
;