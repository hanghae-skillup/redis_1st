CREATE TABLE `movie` (
                       `running_time` int NOT NULL,
                       `created_at` datetime(6) NOT NULL,
                       `id` bigint NOT NULL AUTO_INCREMENT,
                       `modified_at` datetime(6) NOT NULL,
                       `released_date` datetime(6) NOT NULL,
                       `created_by` varchar(100) NOT NULL,
                       `modified_by` varchar(100) NOT NULL,
                       `thumbnail` varchar(50) NOT NULL,
                       `title` varchar(100) NOT NULL,
                       `firm_rating` enum('AGE_12','AGE_15','ALL','LIMITED','RESTRICTED') NOT NULL,
                       `genre` enum('ACTION','ANIMATION','COMEDY','DRAMA','FANTASY','HORROR','ROMANCE','SCI_FI','THRILLER') NOT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- hhcinema.screen definition

CREATE TABLE `screen` (
                        `created_at` datetime(6) NOT NULL,
                        `end_time` datetime(6) NOT NULL,
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `modified_at` datetime(6) NOT NULL,
                        `movie_id` bigint DEFAULT NULL,
                        `start_time` datetime(6) NOT NULL,
                        `theater_id` bigint DEFAULT NULL,
                        `created_by` varchar(100) NOT NULL,
                        `modified_by` varchar(100) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `theater` (
                         `created_at` datetime(6) NOT NULL,
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `modified_at` datetime(6) NOT NULL,
                         `created_by` varchar(100) NOT NULL,
                         `modified_by` varchar(100) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



