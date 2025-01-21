CREATE TABLE movie (
                       id              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       title           VARCHAR(150) NOT NULL,
                       genre           TINYINT UNSIGNED NOT NULL,
                       content_rating  CHAR(1) NOT NULL,
                       release_date    DATE NOT NULL,
                       runtime_minutes SMALLINT UNSIGNED NOT NULL,
                       thumbnail_url   VARCHAR(255) NOT NULL,
                       created_at      DATETIME(6) NOT NULL,
                       created_by      VARCHAR(50) NOT NULL,
                       modified_at     DATETIME(6) NOT NULL,
                       modified_by     VARCHAR(50) NOT NULL
);

CREATE TABLE theater (
                         id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         name        VARCHAR(50) NOT NULL,
                         created_at  DATETIME(6) NOT NULL,
                         created_by  VARCHAR(50) NOT NULL,
                         modified_at DATETIME(6) NOT NULL,
                         modified_by VARCHAR(50) NOT NULL
);

CREATE TABLE screening (
                           id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                           movie_id    INT UNSIGNED NOT NULL,
                           theater_id  INT UNSIGNED NOT NULL,
                           start_time  TIME(6) NOT NULL,
                           end_time    TIME(6) NOT NULL,
                           created_at  DATETIME(6) NOT NULL,
                           created_by  VARCHAR(50) NOT NULL,
                           modified_at DATETIME(6) NOT NULL,
                           modified_by VARCHAR(50) NOT NULL
--                            CONSTRAINT FK_screening_theater FOREIGN KEY (theater_id) REFERENCES theater (id),
--                            CONSTRAINT FK_screening_movie FOREIGN KEY (movie_id) REFERENCES movie (id)
);

CREATE TABLE seat (
                      id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                      theater_id  INT UNSIGNED NOT NULL,
                      seat_row    VARCHAR(1) NOT NULL,  -- A ~ Z 로 제한 가능
                      seat_column TINYINT UNSIGNED NOT NULL,  -- 1 ~ 99 범위 사용
                      created_at  DATETIME(6) NOT NULL,
                      created_by  VARCHAR(50) NOT NULL,
                      modified_at DATETIME(6) NOT NULL,
                      modified_by VARCHAR(50) NOT NULL
--                       CONSTRAINT FK_seat_theater FOREIGN KEY (theater_id) REFERENCES theater (id)
);
