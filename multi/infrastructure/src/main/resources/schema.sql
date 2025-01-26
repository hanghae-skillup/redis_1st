
/*
CREATE TABLE Movies (
                        movie_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        rating VARCHAR(50),
                        release_date DATETIME,
                        thumbnail_url VARCHAR(255),
                        running_time INT,
                        genre VARCHAR(50),
                        created_by VARCHAR(50),
                        created_at DATETIME,
                        updated_by VARCHAR(50),
                        updated_at DATETIME
);

-- Users 테이블
CREATE TABLE Users (
                       user_id BIGINT NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
                       user_name VARCHAR(255) NOT NULL,
                       age        INT,
                       created_by VARCHAR(50),
                       created_at DATETIME,
                       updated_by VARCHAR(50),
                       updated_at DATETIME
);

-- Theater 테이블
CREATE TABLE Theaters (
                         theater_id BIGINT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
                         theater_name VARCHAR(255) NOT NULL,
                         created_by VARCHAR(50),
                         created_at DATETIME,
                         updated_by VARCHAR(50),
                         updated_at DATETIME
);

-- Screenings 테이블
CREATE TABLE Screenings (
                            screening_id BIGINT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
                            movie_id BIGINT NOT NULL,
                            theater_id BIGINT NOT NULL,
                            start_time DATETIME NOT NULL,
                            end_time DATETIME NOT NULL,
                            screening_date DATETIME NOT NULL,
                            created_by VARCHAR(50),
                            created_at DATETIME,
                            updated_by VARCHAR(50),
                            updated_at DATETIME
);

-- SeatReservation 테이블
CREATE TABLE SeatReservations(
                                 seat_reservation_id BIGINT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
                                 screening_id BIGINT NOT NULL,
                                 seat_number VARCHAR(10) NOT NULL,
                                 is_reserved BOOLEAN NOT NULL DEFAULT FALSE,
                                 created_by VARCHAR(50),
                                 created_at DATETIME,
                                 updated_by VARCHAR(50),
                                 updated_at DATETIME
);
*/