CREATE TABLE movie (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    rating VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    thumbnail VARCHAR(255) NOT NULL,
    running_time INT NOT NULL,
    genre VARCHAR(255) NOT NULL
)CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE theater (
    theater_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
)CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE screening (
    screening_id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- screening_id (기본키, 자동 증가)
    movie_id BIGINT NOT NULL,                           -- movie_id (Movie 테이블을 참조, null 불가)
    theater_id BIGINT NOT NULL,                         -- theater_id (Theater 테이블을 참조, null 불가)
    start_time TIME NOT NULL,                           -- start_time (상영 시작 시간, null 불가)
    CONSTRAINT FK_screening_movie FOREIGN KEY (movie_id) REFERENCES movie (movie_id), -- movie_id 외래키
    CONSTRAINT FK_screening_theater FOREIGN KEY (theater_id) REFERENCES theater (theater_id)  -- theater_id 외래키
)CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- user_id (기본키, 자동 증가)
    name VARCHAR(255) NOT NULL,                    -- name (사용자 이름, null 불가)
    age INT NOT NULL                              -- age (사용자 나이, null 불가)
)CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE reservation (
    reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- reservation_id (기본키, 자동 증가)
    user_id BIGINT NOT NULL,                           -- user_id (Users 테이블을 참조, null 불가)
    screening_id BIGINT NOT NULL,                      -- screening_id (Screening 테이블을 참조, null 불가)
    CONSTRAINT FK_reservation_user FOREIGN KEY (user_id) REFERENCES users (user_id),   -- user_id 외래키
    CONSTRAINT FK_reservation_screening FOREIGN KEY (screening_id) REFERENCES screening (screening_id)  -- screening_id 외래키
)CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE seat (
    seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theater_id BIGINT NOT NULL,
    seat_row VARCHAR(255) NOT NULL,
    seat_column INT NOT NULL,
    reservation_id BIGINT,
    version BIGINT,
    CONSTRAINT FK_seat_theater FOREIGN KEY (theater_id) REFERENCES theater (theater_id),  -- theater_id 외래키
    CONSTRAINT FK_seat_reservation FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id)  -- reservation_id 외래키
)CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

ALTER TABLE seat MODIFY COLUMN version BIGINT NOT NULL DEFAULT 0;

INSERT INTO users (name, age) VALUES ("user1", 20);
INSERT INTO users (name, age) VALUES ("user2", 21);
INSERT INTO users (name, age) VALUES ("user3", 22);
INSERT INTO users (name, age) VALUES ("user4", 23);
