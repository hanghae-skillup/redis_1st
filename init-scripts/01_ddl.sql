CREATE TABLE `movie` (
                       `id` int(15) unsigned NOT NULL AUTO_INCREMENT COMMENT '영화 고유ID',
                       `title` varchar(20) NOT NULL COMMENT '영화 제목',
                       `firm_rating` varchar(10) NOT NULL COMMENT '영상물 등급',
                       `genre` varchar(20) NOT NULL COMMENT '장르',
                       `released_date` datetime NOT NULL COMMENT '개봉일',
                       `thumbnail` varchar(200) NOT NULL COMMENT '썸네일',
                       `running_time` int(3) NOT NULL COMMENT '영상 시간',
                       `created_by` varchar(10) NOT NULL DEFAULT 'master' COMMENT '작성자',
                       `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
                       `modified_by` varchar(10) NOT NULL DEFAULT 'master' COMMENT '수정자',
                       `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `theater` (
                         `id` int(15) unsigned NOT NULL AUTO_INCREMENT COMMENT '영화관 고유ID',
                         `name` varchar(15) NOT NULL COMMENT '영화관 명',
                         `created_by` varchar(10) NOT NULL DEFAULT 'master' COMMENT '작성자',
                         `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
                         `modified_by` varchar(10) NOT NULL DEFAULT 'master' COMMENT '수정자',
                         `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `screen` (
                        `id` int(15) unsigned NOT NULL AUTO_INCREMENT COMMENT '상영관 ID',
                        `name` varchar(30) NOT NULL COMMENT '상영관 명',
                        `movie_id` int(15) NOT NULL COMMENT '영화 고유ID',
                        `theater_id` int(15) NOT NULL COMMENT '영화관 고유ID',
                        `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '상영시작시간',
                        `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '상영종료시간',
                        `created_by` varchar(10) NOT NULL DEFAULT 'master' COMMENT '작성자',
                        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
                        `modified_by` varchar(10) NOT NULL DEFAULT 'master' COMMENT '수정자',
                        `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- title 컬럼에 B-Tree 인덱스 생성
CREATE INDEX idx_movie_title ON movie(title);

-- released_date 컬럼에 인덱스 생성
CREATE INDEX idx_movie_released_date ON movie(released_date);


CREATE INDEX idx_screen_time_range ON screen(start_time, end_time);