
use movie;

CREATE TABLE `member` (
                          `member_id`	BIGINT	NOT NULL,
                          `member_name`	VARCHAR(255)	NULL,
                          `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                          `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                          `created_author`	BIGINT	NULL,
                          `updated_author`	BIGINT	NULL
);

CREATE TABLE `movie` (
                         `movie_id`	BIGINT	NOT NULL,
                         `movie_name`	VARCHAR(255)	NULL,
                         `movie_grade`	VARCHAR(255)	NULL	COMMENT 'ENUM',
                         `movie_release_at`	TIMESTAMP	NULL,
                         `movie_image_url`	VARCHAR(255)	NULL,
                         `running_time`	BIGINT	NULL,
                         `movie_genre`	VARCHAR(255)	NULL	COMMENT 'ENUM',
                         `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                         `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                         `created_author`	BIGINT	NULL,
                         `updated_author`	BIGINT	NULL
);

CREATE TABLE `theater` (
                           `theater_id`	BIGINT	NOT NULL,
                           `theater_name`	VARCHAR(255)	NULL,
                           `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                           `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                           `created_author`	BIGINT	NULL,
                           `updated_author`	BIGINT	NULL
);

CREATE TABLE `schedule` (
                            `schedule_id`	BIGINT	NOT NULL,
                            `theater_Id`	BIGINT	NOT NULL,
                            `start_at`	TIMESTAMP	NULL,
                            `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                            `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                            `created_author`	BIGINT	NULL,
                            `updated_author`	BIGINT	NULL
);

CREATE TABLE `seatInfo` (
                            `seatInfo_id`	BIGINT	NOT NULL,
                            `schedule_id`	BIGINT	NOT NULL,
                            `seat_position`	VARCHAR(255)	NULL,
                            `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                            `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                            `created_author`	BIGINT	NULL,
                            `updated_author`	BIGINT	NULL
);

CREATE TABLE `reservation` (
                               `reservation_id`	BIGINT	NOT NULL,
                               `member_id`	BIGINT	NOT NULL,
                               `theater_id`	BIGINT	NOT NULL,
                               `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                               `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                               `created_author`	BIGINT	NULL,
                               `updated_author`	BIGINT	NULL
);

CREATE TABLE `movie_theater_info` (
                                      `movie_theater_info_id`	BIGINT	NOT NULL,
                                      `movie_id`	BIGINT	NOT NULL,
                                      `theater_id`	BIGINT	NOT NULL,
                                      `created_at`	TIMESTAMP	NULL	DEFAULT NOW(),
                                      `updated_at`	TIMESTAMP	NULL	DEFAULT NOW(),
                                      `created_author`	BIGINT	NULL,
                                      `updated_author`	BIGINT	NULL
);

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
                                                             `member_id`
    );

ALTER TABLE `movie` ADD CONSTRAINT `PK_MOVIE` PRIMARY KEY (
                                                           `movie_id`
    );

ALTER TABLE `theater` ADD CONSTRAINT `PK_THEATER` PRIMARY KEY (
                                                               `theater_id`
    );

ALTER TABLE `schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
                                                                 `schedule_id`
    );

ALTER TABLE `seatInfo` ADD CONSTRAINT `PK_SEATINFO` PRIMARY KEY (
                                                                 `seatInfo_id`
    );

ALTER TABLE `reservation` ADD CONSTRAINT `PK_RESERVATION` PRIMARY KEY (
                                                                       `reservation_id`
    );

ALTER TABLE `movie_theater_info` ADD CONSTRAINT `PK_MOVIE_THEATER_INFO` PRIMARY KEY (
                                                                                     `movie_theater_info_id`
    );
