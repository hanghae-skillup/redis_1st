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

CREATE TABLE member (
                        id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                        name        VARCHAR(50) NOT NULL,
                        email       VARCHAR(100) NOT NULL UNIQUE,
                        created_at  DATETIME(6) NOT NULL,
                        created_by  VARCHAR(50) NOT NULL,
                        modified_at DATETIME(6) NOT NULL,
                        modified_by VARCHAR(50) NOT NULL
);

-- 예약 생성 시 변경이 발생하는 테이블
CREATE TABLE reservation (
                             id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                             screening_id INT UNSIGNED NOT NULL,
                             member_id   INT UNSIGNED NOT NULL,
                             created_at  DATETIME(6) NOT NULL,
                             created_by  VARCHAR(50) NOT NULL,
                             modified_at DATETIME(6) NOT NULL,
                             modified_by VARCHAR(50) NOT NULL
--                              CONSTRAINT FK_reservation_screening FOREIGN KEY (screening_id) REFERENCES screening(id) ON DELETE CASCADE,
--                              CONSTRAINT FK_reservation_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
);

CREATE TABLE reserved_seat (
                               id                  INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                               reservation_id      INT UNSIGNED NOT NULL,
                               screening_seat_id   INT UNSIGNED NOT NULL
--                               CONSTRAINT FK_reserved_seat_reservation FOREIGN KEY (reservation_id) REFERENCES reservation(id) ON DELETE CASCADE,
--                               CONSTRAINT FK_reserved_seat_screening_seat FOREIGN KEY (screening_seat_id) REFERENCES screening_seat(id) ON DELETE CASCADE
);

CREATE TABLE screening_seat (
                                id           INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                                screening_id INT UNSIGNED NOT NULL,
                                seat_id      INT UNSIGNED NOT NULL,
                                reserved     BOOLEAN NOT NULL DEFAULT FALSE, -- 좌석 예약 여부
                                version      INT UNSIGNED NOT NULL DEFAULT 0 -- 낙관적 락 적용
--                                CONSTRAINT FK_screening_seat_screening FOREIGN KEY (screening_id) REFERENCES screening(id) ON DELETE CASCADE,
--                                CONSTRAINT FK_screening_seat_seat FOREIGN KEY (seat_id) REFERENCES seat(id) ON DELETE CASCADE,
);
